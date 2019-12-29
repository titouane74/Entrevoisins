package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

    private static final String TAG = "NeighbourActivity";
    private static final String BTN_NOFAVORI = "NOFAVORI";
    private static final String BTN_FAVORI = "FAVORI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour);
        ButterKnife.bind(this);

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

    private void getIncomingIntent() {

        if (getIntent().hasExtra("name") && getIntent().hasExtra("avatarUrl") && getIntent().hasExtra("favori")) {

            String name = getIntent().getStringExtra("name");
            String avatarUrl = getIntent().getStringExtra("avatarUrl");
            boolean isFavori = getIntent().getBooleanExtra("favori",false);

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
