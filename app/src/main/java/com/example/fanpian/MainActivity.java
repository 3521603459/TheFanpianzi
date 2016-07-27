package com.example.fanpian;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.fanpian.adapter.MainFragmentPagerAdapter;
import com.example.fanpian.fragments.DiscoverFragment;
import com.example.fanpian.fragments.PersonalFragment;
import com.example.fanpian.fragments.RecommendFragment;
import com.example.fanpian.fragments.SearchFragment;
import com.example.fanpian.fragments.SettingFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener, View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RadioGroup mRadioGroup;
    private ViewPager mViewPager;
    private ArrayList<Fragment> fragmentList;

    private boolean isRight;
    private ImageView headPortrait;
    private ImageView mainSearch;
    private boolean mFlags;
    private boolean isLeft;
    private boolean mFlags1;
    private boolean isScrolling;
    private double lastValue = -1;
    private LinearLayout mTopLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化视图
        initView();

        // 初始化数据
        initData();

        // 设置适配器
        setAdapter();

        // 设置监听器
        setListener();

    }

    private void initView(){
        headPortrait = (ImageView) findViewById(R.id.main_head_portrait);
        mRadioGroup = (RadioGroup) findViewById(R.id.main_tab_bar);
        mainSearch = (ImageView) findViewById(R.id.main_search);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mTopLinearLayout = (LinearLayout) findViewById(R.id.main_top_layout);
    }

    private void initData() {
        fragmentList=new ArrayList<>();

        fragmentList.add(new SettingFragment());
        fragmentList.add(new PersonalFragment());
        fragmentList.add(new RecommendFragment());
        fragmentList.add(new DiscoverFragment());
        fragmentList.add(new SearchFragment());
    }

    private void setAdapter() {
        mViewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
    }

    private void setListener(){
        // 设置默认显示
        mViewPager.setCurrentItem(2);
        mRadioGroup.check(R.id.main_tab_recommend);

        //设置监听
        mRadioGroup.setOnCheckedChangeListener(this);
        mViewPager.addOnPageChangeListener(this);
        // 头像的点击事件
        headPortrait.setOnClickListener(this);
        // 红色搜索图标点击事件
        mainSearch.setOnClickListener(this);

        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {

                if (mFlags) {
                    if (position > 0 && position <= 1) {
                        Intent intent = new Intent("MainActivity");

                        intent.putExtra("position", position);
                        sendBroadcast(intent);

                        mTopLinearLayout.setAlpha(position);
                    }
                } else if (mFlags1) {
                    if (position > 0 && position <= 1) {
                        Log.d("ppp", position + "");
                        Intent intent = new Intent("MainActivity");
                        intent.putExtra("position", position);
                        sendBroadcast(intent);
                        mTopLinearLayout.setAlpha(1 - position);
                    }
                } else {
                    mTopLinearLayout.setAlpha(1);
                }
            }
        });
    }
    //  RadioGroup的回调方法
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index=0;
        switch (checkedId){
            case R.id.main_tab_personal:
                index=1;
                break;
            case R.id.main_tab_recommend:
                index=2;
                break;
            case R.id.main_tab_discover:
                index=3;
                break;
        }
        mViewPager.setCurrentItem(index);
    }
    // ViewPager的回调方法
    @Override
    public void onPageSelected(int position) {

        Log.d(TAG, "onPageS: "+position);


        if(1<=position&& position<=3){
            switch (position){
                case 1:
                    mRadioGroup.check(R.id.main_tab_personal);
                    break;
                case 2:
                    mRadioGroup.check(R.id.main_tab_recommend);
                    break;
                case 3:
                    mRadioGroup.check(R.id.main_tab_discover);
                    break;
            }
        }

        if(position==3&&isRight){
            mFlags = false;
        }
        if(position==1&& isLeft){
            mFlags1 = false;
        }
    }

    @Override
    public void onPageScrolled(int pos, float arg1, int arg2) {
        Log.d(TAG, "onPageS1: "+pos);

        if(pos==4){
            mTopLinearLayout.setVisibility(View.GONE);
        }

        //pos :当前页面，及你点击滑动的页面
        //arg1:当前页面偏移的百分比
        //arg2:当前页面偏移的像素位置

        if (isScrolling) {
            if (lastValue > arg2) {
                // 递减，向右侧滑动
                isRight = true;
                isLeft = false;
            } else if (lastValue < arg2) {
                // 递增，向左侧滑动
                isRight = false;
                isLeft = true;
            }
//            else if (lastValue == arg2) {
//                isRight = isLeft = true;
//            }
        }

        if((pos == 3 && isLeft)){
            mTopLinearLayout.setVisibility(View.VISIBLE);
            mFlags = true;
        }
        if((pos ==0 && isRight)){
            mTopLinearLayout.setVisibility(View.VISIBLE);
            mFlags1 = true;
        }
        Log.d("===",pos+"");

        lastValue = arg2;

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // 有三种状态（0，1，2）。
        // state == 0 表示什么都没做。
        // state == 1 表示正在滑动
        // state == 2 表示滑动完毕了
        if (state == 1) {
            isScrolling = true;
        } else {
            isScrolling = false;
        }

//        if (state == 2) {
//            isRight = isLeft = false;
//        }
    }

    /**
     *  头像和红色搜索图标点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_head_portrait:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.main_search:
                mViewPager.setCurrentItem(4);
                break;
        }
    }
}
