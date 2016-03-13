package com.xunmeng.jpush.ui.fragment.video;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xunmeng.jpush.R;
import com.xunmeng.jpush.ui.activity.MainActivity;
import com.xunmeng.jpush.ui.fragment.Basefragment;
import com.xunmeng.jpush.utils.MeasureUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
public class VideoFragment extends Basefragment {

    private MainActivity activity;
    private static String KEY_TITLE = "key_yitle";
    private static VideoFragment fragment;
    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static VideoFragment getInstance() {
        if (fragment == null) {
            fragment = new VideoFragment();
            Bundle bundle = new Bundle();
            bundle.putString(KEY_TITLE, "video");
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.video_fragment, container, false);

        initUi(view);

        return view;

    }

    private void initUi(View view) {

        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        List<String> titles = Arrays.asList("热点", "娱乐", "搞笑", "精品","嘻哈","热点", "娱乐", "搞笑", "精品","嘻哈");

        List<VideoListFragment> videoListFragmentlist = new ArrayList<>();
        videoListFragmentlist.add(VideoListFragment.newInstance());
        videoListFragmentlist.add(VideoListFragment.newInstance());
        videoListFragmentlist.add(VideoListFragment.newInstance());
        videoListFragmentlist.add(VideoListFragment.newInstance());
        videoListFragmentlist.add(VideoListFragment.newInstance());
        videoListFragmentlist.add(VideoListFragment.newInstance());
        videoListFragmentlist.add(VideoListFragment.newInstance());
        videoListFragmentlist.add(VideoListFragment.newInstance());
        videoListFragmentlist.add(VideoListFragment.newInstance());
        videoListFragmentlist.add(VideoListFragment.newInstance());

//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
//        LogUtils.e(titles.get(0));
//        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
//        LogUtils.e(titles.get(1));
//        tabLayout.addTab(tabLayout.newTab().setText(titles.get(2)));
//        LogUtils.e(titles.get(2));
//        tabLayout.addTab(tabLayout.newTab().setText(titles.get(3)));
//        LogUtils.e(titles.get(3));

        VideoViewPagerFragmentAdapter adapter = new VideoViewPagerFragmentAdapter(activity, activity.getSupportFragmentManager(), videoListFragmentlist,
                titles);

        viewPager.setAdapter(adapter);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

//        tabLayout.setupWithViewPager(viewPager);

//        dynamicSetTablayoutMode(tabLayout);
    }

    /**
     * 动态修改tab的模式
     *
     * @param tabLayout
     */
    public static void dynamicSetTablayoutMode(TabLayout tabLayout) {
        int tabTotalWidth = 0;
        for (int i = 0; i < tabLayout.getChildCount(); i++) {
            final View view = tabLayout.getChildAt(i);
            view.measure(0, 0);
            tabTotalWidth += view.getMeasuredWidth();
        }
        if (tabTotalWidth <= MeasureUtil.getScreenSize(tabLayout.getContext()).x) {
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }
}
