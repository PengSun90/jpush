package com.xunmeng.jpush.ui.fragment.video;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunmeng.jpush.R;
import com.xunmeng.jpush.ui.fragment.Basefragment;

import java.util.ArrayList;

/**
 * ClassName: VideoListFragment<p>
 * Author: oubowu<p>
 * Fuction: 视频列表界面<p>
 * CreateDate: 2016/2/23 16:54<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public class VideoListFragment extends Basefragment {

    private static VideoListFragment videoListFragment;
    private static String mContent;
    private View view;
    private RecyclerView mRecyclerView;
    private ArrayList<String> mDatas;

    public static VideoListFragment newInstance() {
//        synchronized (VideoListFragment.class) {
//            if (videoListFragment == null) {

                videoListFragment = new VideoListFragment();

//            }
            return videoListFragment;
//        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
//            mContent = savedInstanceState.getString(KEY_CONTENT);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.video_frg_list, container, false);
//        TextView textView = (TextView) view;
//        textView.setText("Fragment #" + mPage);
        initData();
        initUi();
        return view;
    }

    private void initUi() {

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        //设置adapter
        mRecyclerView.setAdapter(new HomeAdapter());
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(
//                getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
    }


    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 20; i++)
        {
            mDatas.add("" + i);
        }
    }


    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.item_video_summary, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.tv.setText(mDatas.get(position));
            holder.img.setImageResource(R.drawable.me);
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;
            ImageView img;

            public MyViewHolder(View view)
            {
                super(view);
                img = (ImageView)view.findViewById(R.id.iv_video_summary);
                tv = (TextView) view.findViewById(R.id.tv_video_summary);
            }
        }
    }
}
