package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavoriEvent;


import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class FavoriFragment extends Fragment {

    private static String TAG = "FAVORIFRAGMENT";
    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;

    public FavoriFragment() {}

    /**
     * Create and return a new instance
     * @return @{@link FavoriFragment}
     */
    public static FavoriFragment newInstance() {
        FavoriFragment fragment = new FavoriFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_favori_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        initListFavori();
        return view;
    }

    /**
     * Init the List of neighbours Favorites
     */
    private void initListFavori() {
        mNeighbours = mApiService.getNeighboursFavori();
        mRecyclerView.setAdapter(new MyFavoriRecyclerViewAdapter(mNeighbours));
    }

    /**
     * Rafraîchit la liste des favoris à partir de la liste globale des voisins en cours
     * en appelant une méthode dans l'API Service
     */
    public void refreshList () {

        mNeighbours = mApiService.getNeighboursFavori();
        mRecyclerView.setAdapter(new MyFavoriRecyclerViewAdapter(mNeighbours));

    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    /**
     * Rafraîchit la liste des favoris à chaque retour sur l'activité
     */
    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteFavori(DeleteFavoriEvent event) {
        mApiService.changeStatutFavori(event.neighbour,false);
        initListFavori();
    }

}
