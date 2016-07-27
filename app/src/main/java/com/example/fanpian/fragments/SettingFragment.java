package com.example.fanpian.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.fanpian.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {


    private RelativeLayout mRelativeLayout;
    private ImageView backImageView;
    private BroadcastReceiver mReceiver;


    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        initView(view);
        initData();

        return view;
    }

    private void initView(View view) {

        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.setting_top_layout);
        backImageView = (ImageView) view.findViewById(R.id.back_setting_fragment);

    }

    private void initData() {

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                float position = intent.getFloatExtra("position", 0);

                Log.d("ooo", position + "");
                mRelativeLayout.setAlpha(position);
            }
        };
    }

}