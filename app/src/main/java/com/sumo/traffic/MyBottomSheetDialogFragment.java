package com.sumo.traffic;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {

    String mString;
   static RecyclerView mRecyclerView;
    static VerticalListAdapter mVerticalListAdapter;
   static List <Model> list;


    static MyBottomSheetDialogFragment newInstance(String string) {
        MyBottomSheetDialogFragment f = new MyBottomSheetDialogFragment();
        Bundle args = new Bundle();
        args.putString("string", string);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mString = getArguments().getString("string");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_modal_list, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<Model>();
        list.add(new Model(R.drawable.rarz, "Adjust Radius"));
        list.add(new Model(R.drawable.really, "Real Estate"));
        list.add(new Model(R.drawable.places, "Places To Go"));
        list.add(new Model(R.drawable.userneedz, "User's Need"));
        list.add(new Model(R.drawable.storez, "Store"));
        list.add(new Model(R.drawable.transpo, "Transportation"));






        mVerticalListAdapter = new VerticalListAdapter(getContext(), list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mVerticalListAdapter);



    }



    }