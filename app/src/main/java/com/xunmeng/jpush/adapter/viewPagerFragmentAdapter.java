package com.xunmeng.jpush.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xunmeng.jpush.fragment.news.NewsListFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/3/7.
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {


    private final List<NewsListFragment> mFragments;
    private final List<String> mTitles;
    private Context mcontext = null;

    private FragmentManager mFragmentManager;

    /**
     * 更新频道，前面固定的不更新，后面一律更新
     *
     * @param fragments
     * @param titles
     */
    public void updateFragments(List<NewsListFragment> fragments, List<String> titles) {
        this.mFragments.addAll(fragments);
        this.mTitles.addAll(titles);
        notifyDataSetChanged();
    }


    public ViewPagerFragmentAdapter(Context context, FragmentManager fm, List<NewsListFragment> fragments, List<String> titles) {
        super(fm);
        this.mcontext = context;
        this.mFragmentManager = fm;
        this.mFragments = fragments;
        this.mTitles = titles;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).toUpperCase();

    }

    @Override
    public int getCount() {
        return mTitles.size();
    }
}
