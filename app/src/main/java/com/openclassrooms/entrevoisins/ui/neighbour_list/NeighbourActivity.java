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
    @BindView(R.id.cardAddressNeighbour)
    TextView mCardAddressNeighbour;
    @BindView(R.id.cardPhoneNeighbour)
    TextView mCardPhoneNeighbour;
    @BindView(R.id.cardSiteNeighbour)
    TextView mCardSiteNeighbour;
    @BindView(R.id.btnfloat_favoris)
    FloatingActionButton mBtnFloatFavoris;
    @BindView(R.id.toolbar_detail)
    Toolbar mToolbarDetail;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout lCollapsingToolbarLayout ;
    @BindView(R.id.cardTextAboutMe)
    TextView lCardTextAboutMe;

    private NeighbourApiService mApiService;

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
        if (getIntent().hasExtra("position") && getIntent().hasExtra("parent"))
        {
            int lPosition = getIntent().getIntExtra("position",0);

            if (getIntent().getStringExtra("parent").equals(PARENT_FAVORI)) {
                Neighbour lNeighbour = mNeighboursFavori.get(lPosition);
                if (mNeighbours.contains(mNeighboursFavori.get(lPosition))) {
                    mNeighbour = mNeighbours.get(mNeighbours.indexOf(lNeighbour));
                }
            } else {
                mNeighbour = mNeighbours.get(lPosition);
            }
            setInfoNeighbour(mNeighbour);
        }
    }

    /**
     * Affichage des informations du voisin avec les valeurs reçues
     * @param pNeighbour : object : voisin à afficher
     */
    private void setInfoNeighbour(Neighbour pNeighbour) {

        lCollapsingToolbarLayout.setTitle(pNeighbour.getName());
        mCardNameNeighbour.setText(pNeighbour.getName());

        Glide.with(this)
                .asBitmap()
                .load(pNeighbour.getAvatarUrl())
                .into(mImgNeighbour);

        mCardAddressNeighbour.setText(pNeighbour.getAddress());
        mCardPhoneNeighbour.setText(pNeighbour.getPhone());
        mCardSiteNeighbour.setText(pNeighbour.getWebSite());

        if (pNeighbour.isFavori()) {
            changeStatutFavori(true);
        } else {
            changeStatutFavori(false);
        }

        lCardTextAboutMe.setText(pNeighbour.getAboutMe());
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
}
