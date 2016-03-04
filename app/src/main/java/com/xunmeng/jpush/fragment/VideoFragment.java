package com.xunmeng.jpush.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xunmeng.jpush.R;

/**
 * Created by Administrator on 2016/3/3.
 */
public class VideoFragment extends Basefragment {

    private Activity activity;
    private static String KEY_TITLE = "key_yitle";
    private static VideoFragment fragment;

    private VideoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public static VideoFragment getInstance() {
        if (fragment == null) {
            fragment = new VideoFragment();
            Bundle bundle = new Bundle();
            bundle.putString(KEY_TITLE, "news");
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.video_frg_ll, container, false);
//        Toast.makeText(activity, activity.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
        return view;

    }
}
