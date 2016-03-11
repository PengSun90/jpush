package com.xunmeng.jpush.fragment.read;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xunmeng.jpush.R;
import com.xunmeng.jpush.fragment.Basefragment;

/**
 * Created by Administrator on 2016/3/3.
 */
public class ReadFragment extends Basefragment {

    private Activity activity;
    private static String KEY_TITLE = "key_yitle";
    private static ReadFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public static ReadFragment getInstance() {
        if (fragment == null) {
            fragment = new ReadFragment();
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

        View view = inflater.inflate(R.layout.read_frg_ll, container, false);
//        Toast.makeText(activity, activity.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
        return view;

    }
}
