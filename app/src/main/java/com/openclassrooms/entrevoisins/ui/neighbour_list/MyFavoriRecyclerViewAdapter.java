package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavoriEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourActivity.PARENT_FAVORI;

public class MyFavoriRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriRecyclerViewAdapter.ViewHolder> {

    private final String TAG = "MyFavoriRVAdap";
    public static  List<Neighbour> mNeighboursFavori;
    private Context mContext;

    public MyFavoriRecyclerViewAdapter(List<Neighbour> items) {
        mNeighboursFavori = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favori, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour mNeighbour = mNeighboursFavori.get(position);
        holder.mNeighbourName.setText(mNeighbour.getName());

        mContext = holder.mNeighbourName.getContext();

        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(mNeighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteFavoriEvent(mNeighbour));
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent lIntentNeighbourActvitity = new Intent(mContext, NeighbourActivity.class);
                lIntentNeighbourActvitity.putExtra("name", mNeighbour.getName());
                lIntentNeighbourActvitity.putExtra("avatarUrl", mNeighbour.getAvatarUrl());
                lIntentNeighbourActvitity.putExtra("favori", mNeighbour.isFavori());
                lIntentNeighbourActvitity.putExtra("position",position);
                lIntentNeighbourActvitity.putExtra("parent", PARENT_FAVORI);
                mContext.startActivity(lIntentNeighbourActvitity);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNeighboursFavori.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar_fav)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name_fav)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button_fav)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}