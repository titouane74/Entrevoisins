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
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriRecyclerViewAdapter.ViewHolder> {

    private final String TAG = "MyNeighbourRVAdap";
    private final List<Neighbour> mNeighbours;
    private Context mContext;

    public MyFavoriRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favori, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());

        mContext = holder.mNeighbourName.getContext();

        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

/*        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });*/

        holder.mNeighbourName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNeighbourActivity(neighbour);
            }
        });
        holder.mNeighbourAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNeighbourActivity(neighbour);
            }
        });
    }

    public void openNeighbourActivity(Neighbour pNeighbour) {

        Intent lIntentNeighbourActvitity = new Intent(mContext, NeighbourActivity.class);
        lIntentNeighbourActvitity.putExtra("name", pNeighbour.getName());
        lIntentNeighbourActvitity.putExtra("avatarUrl", pNeighbour.getAvatarUrl());
        lIntentNeighbourActvitity.putExtra("favori", pNeighbour.isFavori());
        mContext.startActivity(lIntentNeighbourActvitity);
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}