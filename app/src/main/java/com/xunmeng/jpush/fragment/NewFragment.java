package com.xunmeng.jpush.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TabPageIndicator;
import com.xunmeng.jpush.R;
import com.xunmeng.jpush.adapter.viewPagerFragmentAdapter;
import com.xunmeng.jpush.utils.LogUtils;

/**
 * Created by Administrator on 2016/3/3.
 */
public class NewFragment extends Basefragment {

    private AppCompatActivity activity;
    private static String KEY_TITLE = "key_yitle";
    private static NewFragment fragment;

    public static NewFragment getInstance() {
        if (fragment == null) {
            fragment = new NewFragment();
            Bundle bundle = new Bundle();
            bundle.putString(KEY_TITLE, "news");
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (AppCompatActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            LogUtils.d("unnomal");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.news_frg_ll, container, false);
//      Toast.makeText(activity, activity.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();

        FragmentPagerAdapter adapter = new viewPagerFragmentAdapter(activity,activity.getSupportFragmentManager());

        ViewPager news_pager = (ViewPager) view.findViewById(R.id.news_pager);
        news_pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator) view.findViewById(R.id.news_indicator);
        indicator.setViewPager(news_pager);

        return view;

    }



}
