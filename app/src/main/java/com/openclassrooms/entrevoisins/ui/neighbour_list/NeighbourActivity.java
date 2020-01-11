package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity.mDecorView;
import static com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity.mUiOptions;
import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyFavoriRecyclerViewAdapter.mNeighboursFavori;
import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.mNeighbours;

/**
 * Created by Florence LE BOURNOT on 27/12/2019
 */
public class NeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.img_Neighbour)
    ImageView mImgNeighbour;
    @BindView(R.id.cardNameNeighbour)
    TextView mCardNameNeighbour;
    @BindView(R.id.btnfloat_favoris)
    FloatingActionButton mBtnFloatFavoris;
    @BindView(R.id.toolbar_detail)
    Toolbar mToolbarDetail;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout lCollapsingToolbarLayout ;
    @BindView(R.id.cardTextAboutMe)
    TextView lCardTextAboutMe;

    private NeighbourApiService mApiService;

    private static final String TAG = "NeighbourActivity";
    private static final String BTN_NOFAVORI = "NOFAVORI";
    private static final String BTN_FAVORI = "FAVORI";
    public static final String PARENT_FAVORI = "PARENT_FAVORI";
    public static final String PARENT_NEIGHBOUR = "PARENT_NEIGHBOUR";

    private Neighbour mNeighbour;
    /**
     * Sur ouverture de l'activité
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();

        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        mDecorView = getWindow().getDecorView();
        mUiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        mDecorView.setSystemUiVisibility(mUiOptions);

        setSupportActionBar(mToolbarDetail);

        if (getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }

        getIncomingIntent();

        mBtnFloatFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                afficheBtnFloatFavorisOnClick();
            }
        });
    }

    /**
     * Sur sélection du bouton retour de la toolbar ferme le détail du voisin
     * @param pItem : menuitem : retour
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem pItem) {
        if (pItem.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(pItem);
    }

    /**
     * Vérifie que tous les éléments passés en argument sont bien alimentés
     */
    private void getIncomingIntent() {
        if (getIntent().hasExtra("name") && getIntent().hasExtra("avatarUrl") && getIntent().hasExtra("favori") && getIntent().hasExtra("position")) {

            String name = getIntent().getStringExtra("name");
            String avatarUrl = getIntent().getStringExtra("avatarUrl");
            Boolean isFavori = getIntent().getBooleanExtra("favori",false);
            String aboutMe = getIntent().getStringExtra("aboutme");
            int lPosition = getIntent().getIntExtra("position",0);


            //Liste mise à jour sera toujours la liste du recycler Neighbours car contient tous les voisons
            // et permet l'actualisation de la liste des voisins favoris au moment de du refreshList
            if (getIntent().getStringExtra("parent").equals(PARENT_FAVORI)) {
                Neighbour lNeighbour = mNeighboursFavori.get(lPosition);
                if (mNeighbours.contains( mNeighboursFavori.get(lPosition))) {
                    mNeighbour = mNeighbours.get(mNeighbours.indexOf(lNeighbour));
                }
            } else {
                mNeighbour = mNeighbours.get(lPosition);
            }
            setInfoNeighbour(name, avatarUrl, isFavori,aboutMe);

        }
    }

    /**
     * Affichage des informations du voisin avec les valeurs reçues
     * @param pName : string : nom du voisin
     * @param pAvatarUrl : string : image du voisin
     * @param pIsFavori : boolean : indicateur de favori ou non
     */
    private void setInfoNeighbour(String pName, String pAvatarUrl, Boolean pIsFavori, String pAboutMe) {

        lCollapsingToolbarLayout.setTitle(pName);
        mCardNameNeighbour.setText(pName);

        Glide.with(this)
                .asBitmap()
                .load(pAvatarUrl)
                .into(mImgNeighbour);

        if (pIsFavori) {
            changeStatutFavori(true);
        } else {
            changeStatutFavori(false);
        }

        lCardTextAboutMe.setText(pAboutMe);
    }

    /**
     * Gestion de l'affichage sur le click du bouton et de la mise à jour de la liste des voisins
     */
    private void  afficheBtnFloatFavorisOnClick() {

        if ( mBtnFloatFavoris.getTag() == BTN_FAVORI) {
            Toast.makeText(this, getString(R.string.txt_retrait_favori), Toast.LENGTH_SHORT).show();
            mApiService.changeStatutFavori(mNeighbour,false);
            changeStatutFavori(false);
        } else {
            Toast.makeText(this, getString(R.string.txt_ajout_favori), Toast.LENGTH_SHORT).show();
            mApiService.changeStatutFavori(mNeighbour,true);
            changeStatutFavori(true);
        }

    }

    /**
     * Change le statut du bouton Favori en fonction de la valeur reçue
     * @param pIsFavori : boolean : true si le bouton doit affiché Favori, false si le bouton ne doit pas afficher Favori
     */
    private void changeStatutFavori(boolean pIsFavori) {
        if (pIsFavori) {
            mBtnFloatFavoris.setImageResource(R.drawable.ic_star_yellow_24dp);
            mBtnFloatFavoris.setTag(BTN_FAVORI);
        } else {
            mBtnFloatFavoris.setImageResource(R.drawable.ic_star_border_yellow_24dp);
            mBtnFloatFavoris.setTag(BTN_NOFAVORI);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDecorView = getWindow().getDecorView();
        mDecorView.setSystemUiVisibility(mUiOptions);
    }
}
