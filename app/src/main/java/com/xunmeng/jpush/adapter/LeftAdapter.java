package com.xunmeng.jpush.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunmeng.jpush.R;
import com.xunmeng.jpush.entity.MainDrawerMenu;

import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class LeftAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private List<MainDrawerMenu> list_menu;                   //菜单名称与图标的list,采用了一个类

    public LeftAdapter(Context context,List<MainDrawerMenu> list_menu) {
        inflater = LayoutInflater.from(context);
        this.list_menu = list_menu;
    }

    @Override
    public int getCount() {
        return list_menu.size();
    }

    @Override
    public Object getItem(int position) {
        return list_menu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView==null)
        {
            convertView = inflater.inflate(R.layout.main_drawer_item,null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView)convertView.findViewById(R.id.item_icon);
            viewHolder.title = (TextView)convertView.findViewById(R.id.item_title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.icon.setImageResource(list_menu.get(position).getMainDrawer_icon());
        viewHolder.title.setText(list_menu.get(position).getMainDrawer_menuName());
        return convertView;
    }

    public class ViewHolder
    {
        ImageView icon;
        TextView title;
    }
}
