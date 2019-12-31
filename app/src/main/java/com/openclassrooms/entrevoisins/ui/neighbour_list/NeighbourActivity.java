package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Florence LE BOURNOT on 27/12/2019
 */
public class NeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.nameNeighbour_tv)
    TextView mNameNeigbourTv;
    @BindView(R.id.imgbtn_return)
    ImageButton mImgBtnReturn;
    @BindView(R.id.img_Neighbour)
    ImageView mImgNeighbour;
    @BindView(R.id.cardNameNeighbour)
    TextView mCardNameNeighbour;
    @BindView(R.id.btnfloat_favoris)
    FloatingActionButton mBtnFloatFavoris;
    @BindView(R.id.toolbar_detail)
    Toolbar mToolbarDetail;

    private static final String TAG = "NeighbourActivity";
    private static final String BTN_NOFAVORI = "NOFAVORI";
    private static final String BTN_FAVORI = "FAVORI";

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



        mImgBtnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBtnFloatFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mBtnFloatFavoris.getTag() == BTN_FAVORI) {
                    mBtnFloatFavoris.setImageResource(R.drawable.ic_star_border_yellow_24dp);
                    mBtnFloatFavoris.setTag(BTN_NOFAVORI);
                } else {
                    mBtnFloatFavoris.setImageResource(R.drawable.ic_star_yellow_24dp);
                    mBtnFloatFavoris.setTag(BTN_FAVORI);
                }
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

        if (getIntent().hasExtra("name") && getIntent().hasExtra("avatarUrl") && getIntent().hasExtra("favori") && getIntent().hasExtra("id")) {

            String name = getIntent().getStringExtra("name");
            String avatarUrl = getIntent().getStringExtra("avatarUrl");
            boolean isFavori = getIntent().getBooleanExtra("favori",false);
            int id = getIntent().getIntExtra("id",0);

            setInfoNeighbour(name, avatarUrl,isFavori);
        }
    }

    private void setInfoNeighbour(String name, String avatarUrl,boolean isFavori) {

        mNameNeigbourTv.setText(name);
        mCardNameNeighbour.setText(name);

        Glide.with(this)
                .asBitmap()
                .load(avatarUrl)
                .into(mImgNeighbour);


        if (isFavori) {
            mBtnFloatFavoris.setImageResource(R.drawable.ic_star_yellow_24dp);
            mBtnFloatFavoris.setTag(BTN_FAVORI);
        } else {
            mBtnFloatFavoris.setImageResource(R.drawable.ic_star_border_yellow_24dp);
            mBtnFloatFavoris.setTag(BTN_NOFAVORI);
        }
    }

}
