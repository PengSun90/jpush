package com.xunmeng.jpush.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xunmeng.jpush.R;
import com.xunmeng.jpush.fragment.BaseAbcFragment;
import com.xunmeng.jpush.fragment.ContentFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/3/7.
 */
public class viewPagerFragmentAdapter extends FragmentPagerAdapter {


    private final List<BaseAbcFragment> mFragments;
    private final List<String> mTitles;
    private Context mcontext = null;
    private String[] CONTENT;
    private FragmentManager mFragmentManager;

    /**
     * 更新频道，前面固定的不更新，后面一律更新
     *
     * @param fragments
     * @param titles
     */
    public void updateFragments(List<BaseAbcFragment> fragments, List<String> titles) {
        this.mFragments.addAll(fragments);
        this.mTitles.addAll(titles);
        notifyDataSetChanged();
    }


    public viewPagerFragmentAdapter(Context context, FragmentManager fm ,List<BaseAbcFragment> fragments, List<String> titles) {
        super(fm);
        this.mcontext = context;
        this.mFragmentManager = fm;
        this.mFragments = fragments;
        this.mTitles = titles;
        CONTENT = context.getResources().getStringArray(R.array.news_title);
    }

    @Override
    public Fragment getItem(int position) {
        return ContentFragment.newInstance(CONTENT[position % CONTENT.length]);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return CONTENT[position % CONTENT.length].toUpperCase();
    }

    @Override
    public int getCount() {
        return CONTENT.length;
    }
}
