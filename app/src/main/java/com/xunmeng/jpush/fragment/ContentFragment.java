package com.xunmeng.jpush.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.xunmeng.jpush.R;
import com.xunmeng.jpush.adapter.jazzViewPagerAdapter;
import com.xunmeng.jpush.adapter.newsListViewAdapter;
import com.xunmeng.jpush.widget.jazzViewPager.JazzyViewPager;
import com.xunmeng.jpush.widget.xListView.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/5.
 */

public final class ContentFragment extends Basefragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String KEY_CONTENT = "TestFragment:Content";
    private View view;
    private JazzyViewPager jazzyViewPager;
    public int MSG_CHANGE_PHOTO = 1;
    public int PHOTO_CHANGE_TIME = 3000;//延时时间

    public int x = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    int index = jazzyViewPager.getCurrentItem();
                    if (index == 3 /*mImageList.size() - 1*/) {
                        x = -1;
                    } else if (index == 0) {
                        x = 1;
                    }
                    jazzyViewPager.setCurrentItem((index + x));
                    mHandler.sendEmptyMessageDelayed(MSG_CHANGE_PHOTO, PHOTO_CHANGE_TIME);
                    break;
            }
        }

    };
    private Activity activity;
    private XListView listView;

    public static ContentFragment newInstance(String content) {
        ContentFragment fragment = new ContentFragment();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append(content).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        fragment.mContent = builder.toString();

        return fragment;
    }

    private String mContent = "";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null != view) {
            ViewGroup parent = (ViewGroup) (view).getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        } else {
            view = inflater.inflate(R.layout.news_viewpager, null);
            initUi();
        }
        return view;
    }

    private newsListViewAdapter newsListViewAdapter;

    private void initUi() {
        initJazzyViewPager();

        // TODO: 2016/3/7 test;

        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("aaa");
        listView = (XListView) view.findViewById(R.id.news_listview);
        newsListViewAdapter = (newsListViewAdapter) listView.getAdapter();
        if (newsListViewAdapter == null) {
            newsListViewAdapter = new newsListViewAdapter(activity, list);
            listView.setAdapter(new newsListViewAdapter(activity, list));
            listView.setXListViewListener(new XListView.IXListViewListener() {
                @Override
                public void onRefresh() {
                    List<String> list = new ArrayList<>();
                    list.add("aaa");
                    list.add("aaa");
                    list.add("aaa");
                    list.add("aaa");
                    newsListViewAdapter.updataList(list);
                    listView.stopRefresh();
                }

                @Override
                public void onLoadMore() {

                }
            });
        } else {
            newsListViewAdapter.updataList(list);
        }

        listView.setOnItemClickListener(this);
    }

    private void initJazzyViewPager() {
        jazzyViewPager = (JazzyViewPager) view.findViewById(R.id.news_viewpager);
        jazzyViewPager.setOnClickListener(this);
        jazzyViewPager.setTransitionEffect(JazzyViewPager.TransitionEffect.CubeOut);
        // TODO: 2016/3/6  加图片路径
        jazzyViewPager.setAdapter(new jazzViewPagerAdapter(activity,/* 加图片路径*/ null));
        jazzyViewPager.setCurrentItem(0);
        mHandler.sendEmptyMessageDelayed(MSG_CHANGE_PHOTO, PHOTO_CHANGE_TIME);


    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
