package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        Log.d(TAG, "getIncomingIntent: checking for incoming intents");

        if (getIntent().hasExtra("name") && getIntent().hasExtra("avatarUrl")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");

            String name = getIntent().getStringExtra("name");
            String avatarUrl = getIntent().getStringExtra("avatarUrl");

            setInfoNeighbour(name, avatarUrl);
        }
    }

    private void setInfoNeighbour(String name, String avatarUrl) {
        Log.d(TAG, "setInfoNeighbour: setting the image and the name");

        mNameNeigbourTv.setText(name);
        mCardNameNeighbour.setText(name);

        Glide.with(this)
                .asBitmap()
                .load(avatarUrl)
                .into(mImgNeighbour);

    }

}
