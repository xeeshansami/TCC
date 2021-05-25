package com.dk.tengerms.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dk.tengerms.R;
import com.dk.tengerms.models.mFilterDashboard;
import com.dk.tengerms.views.adapters.BuyOffersAdapter;
import com.dk.tengerms.views.adapters.RecyclerViewAdapter;

import java.util.ArrayList;


public class BuyOffer extends Fragment {
    RecyclerView rvBuyOffers;
    private BuyOffersAdapter buyOffersAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buy_offer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        rvBuyOffers = view.findViewById(R.id.rvBuyOffers);
        buyOffersRecyclerView();
    }

    public void buyOffersRecyclerView() {
        ArrayList<mFilterDashboard> rec = new ArrayList<>();
        ArrayList<String> txt = new ArrayList<>();
        txt.add("1000+ package for order");
        txt.add("800 attractions & lesure offers");
        txt.add("Includes delivery offers");
        txt.add("1,112 hotel offers");
        txt.add("Branches, drinks  attractions");
        for (int i = 0; i < txt.size(); i++) {
            mFilterDashboard filterDashboard = new mFilterDashboard();
            filterDashboard.setTxt(txt.get(i));
            rec.add(filterDashboard);
        }
        // set up the RecyclerView
        rvBuyOffers.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        buyOffersAdapter = new BuyOffersAdapter(getActivity(), rec);
        rvBuyOffers.setAdapter(buyOffersAdapter);
    }
}