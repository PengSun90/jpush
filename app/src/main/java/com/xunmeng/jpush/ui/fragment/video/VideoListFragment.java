package com.xunmeng.jpush.ui.fragment.video;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    private HomeAdapter mStaggeredHomeAdapter;

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

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        mStaggeredHomeAdapter = new HomeAdapter(getActivity(),mDatas);
        //设置adapter
        mRecyclerView.setAdapter(mStaggeredHomeAdapter);
        mRecyclerView.setHasFixedSize(true);

        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(
//                getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        initEvent();
    }
    private void initEvent()
    {
        mStaggeredHomeAdapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(),
                        position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity(),
                        position + " long click", Toast.LENGTH_SHORT).show();
            }
        });
    }


    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 200; i++) {
            mDatas.add("" + i);
        }
    }
}
