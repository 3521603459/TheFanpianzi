package com.example.fanpian.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.fanpian.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class SearchFragment extends Fragment {


    private BroadcastReceiver mReceiver;
    private LinearLayout mTopLinearLayout;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        getContext().unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume", "是");
        //  动态注册广播接收者用于接收透明度数值的广播

        IntentFilter filter = new IntentFilter("MainActivity");

        getContext().registerReceiver(mReceiver, filter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        initView(view);

        initData();
        return view;
    }

    private void initView(View view) {

        mTopLinearLayout = (LinearLayout) view.findViewById(R.id.search_top_linearlayout);
    }

    private void initData() {

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                float position = intent.getFloatExtra("position", 0);

                Log.d("ooo", position + "");
                mTopLinearLayout.setAlpha(1 - position);
            }
        };

    }

}

