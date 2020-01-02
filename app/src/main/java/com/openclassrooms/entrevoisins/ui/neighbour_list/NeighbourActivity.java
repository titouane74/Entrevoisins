package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

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
    @BindView(R.id.btnfloat_favoris)
    FloatingActionButton mBtnFloatFavoris;
    @BindView(R.id.toolbar_detail)
    Toolbar mToolbarDetail;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout lCollapsingToolbarLayout ;

    private static final String TAG = "NeighbourActivity";
    private static final String BTN_NOFAVORI = "NOFAVORI";
    private static final String BTN_FAVORI = "FAVORI";
    public static final String PARENT_FAVORI = "PARENT_FAVORI";
    public static final String PARENT_NEIGHBOUR = "PARENT_NEIGHBOUR";

    private Neighbour mNeighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour);
        ButterKnife.bind(this);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem pItem) {
        if (pItem.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(pItem);
    }



    private void getIncomingIntent() {
        if (getIntent().hasExtra("name") && getIntent().hasExtra("avatarUrl") && getIntent().hasExtra("favori") && getIntent().hasExtra("position")) {

            String name = getIntent().getStringExtra("name");
            String avatarUrl = getIntent().getStringExtra("avatarUrl");
            Boolean isFavori = getIntent().getBooleanExtra("favori",false);
            int lPosition = getIntent().getIntExtra("position",0);
//            Log.d(TAG, "getIncomingIntent: extra " + lPosition);
//            Log.d(TAG, "getIncomingIntent: " + (getIntent().getStringExtra("parent")));


            if (getIntent().getStringExtra("parent").equals(PARENT_FAVORI)) {
                Neighbour lNeighbour = mNeighboursFavori.get(lPosition);
                Log.d(TAG, "getincomingIntent: " + lNeighbour.getName() + " - " + lNeighbour.isFavori() + " - " + lPosition);
                if (mNeighbours.contains( mNeighboursFavori.get(lPosition))) {
                    mNeighbour = mNeighbours.get(mNeighbours.indexOf(lNeighbour));
                    Log.d(TAG, "getincomingIntent: " + mNeighbour.getName() + " - " + mNeighbour.isFavori() + " - " + lPosition);
                }
            } else {
                mNeighbour = mNeighbours.get(lPosition);
            }
            setInfoNeighbour(name, avatarUrl, isFavori);

        }
    }

    private void setInfoNeighbour(String pName, String pAvatarUrl, Boolean pIsFavori) {

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
    }

    private void  afficheBtnFloatFavorisOnClick() {

        if ( mBtnFloatFavoris.getTag() == BTN_FAVORI) {
            Toast.makeText(this, "Voisin retiré de la liste des favoris", Toast.LENGTH_SHORT).show();
            mNeighbour.setFavori(false);
//            Log.d(TAG, "afficheBtnFloatFavoris1: " + mNeighbour.getName() + " - " + mNeighbour.isFavori());
            changeStatutFavori(false);
        } else {
            Toast.makeText(this, "Voisin ajouté à la liste des favoris", Toast.LENGTH_SHORT).show();
            mNeighbour.setFavori(true);
//            Log.d(TAG, "afficheBtnFloatFavoris2: " + mNeighbour.getName() + " - " + mNeighbour.isFavori());
            changeStatutFavori(true);
        }
    }

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
