package com.xunmeng.jpush.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xunmeng.jpush.R;
import com.xunmeng.jpush.fragment.ContentFragment;

/**
 * Created by Administrator on 2016/3/7.
 */
public class viewPagerFragmentAdapter extends FragmentPagerAdapter {


    private Context mcontext = null;
    private String[] CONTENT;


    public viewPagerFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mcontext = context;
        CONTENT = mcontext.getResources().getStringArray(R.array.news_title);
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
