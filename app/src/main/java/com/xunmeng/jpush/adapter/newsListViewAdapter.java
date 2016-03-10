package com.xunmeng.jpush.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xunmeng.jpush.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/7.
 */
public class newsListViewAdapter extends BaseAdapter {


    private Context context;
    private List<String> list = new ArrayList<>();

    public newsListViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public void updataList(List<String> listUpdata) {
        if (listUpdata != null) {
            list.addAll(listUpdata);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.news_item, null);

        convertView = view;
        return convertView;
    }
}
