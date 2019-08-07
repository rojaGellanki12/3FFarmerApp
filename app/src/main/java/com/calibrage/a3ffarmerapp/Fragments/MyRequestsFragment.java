package com.calibrage.a3ffarmerapp.Fragments;


import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.calibrage.a3ffarmerapp.Adapters.MyReqListAdapter;
import com.calibrage.a3ffarmerapp.Model.MyReqListData;
import com.calibrage.a3ffarmerapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyRequestsFragment extends Fragment {


    public MyRequestsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_requests,
                container, false);
        MyReqListData[] myListData = new MyReqListData[] {
                new MyReqListData(getResources().getString(R.string.labour_request), android.R.drawable.ic_dialog_info),
                new MyReqListData(getResources().getString(R.string.fertilizer_request), android.R.drawable.ic_dialog_info),

        };

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        MyReqListAdapter adapter = new MyReqListAdapter(getContext(),myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

}


