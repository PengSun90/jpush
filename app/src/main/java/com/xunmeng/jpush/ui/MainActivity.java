package com.xunmeng.jpush.ui;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.xunmeng.jpush.LeftAdapter;
import com.xunmeng.jpush.R;
import com.xunmeng.jpush.entity.MainDrawerMenu;
import com.xunmeng.jpush.fragment.FindFragment;
import com.xunmeng.jpush.fragment.NewFragment;
import com.xunmeng.jpush.fragment.ReadFragment;
import com.xunmeng.jpush.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout news;
    private LinearLayout read;
    private LinearLayout video;
    private LinearLayout find;

    private NewFragment newFragment;
    private VideoFragment videoFragment;
    private ReadFragment readFragment;
    private FindFragment findFragment;
    private Fragment currentFragment;
    private ImageView head_icon;
    private ListView list_found;
    private LinearLayout about_me;
    private LinearLayout setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initUi();
        setDefaultFragment();

    }


    private void initUi() {
        initToolbar();
        head_icon = (ImageView) findViewById(R.id.icon_view);
        head_icon.setOnClickListener(this);

        list_found = (ListView) findViewById(R.id.lv_main_drawer_leftmenu);
        list_found.setAdapter(new LeftAdapter(this, getMenuItem()));
        list_found.setOnItemClickListener(this);

        about_me = (LinearLayout)findViewById(R.id.about_me);
        about_me.setOnClickListener(this);

        setting = (LinearLayout)findViewById(R.id.setting);
        setting.setOnClickListener(this);

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tb_main);
        toolbar.setTitle("Toolbar");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        news = (LinearLayout) findViewById(R.id.news);
        news.setOnClickListener(this);

        read = (LinearLayout) findViewById(R.id.read);
        read.setOnClickListener(this);

        video = (LinearLayout) findViewById(R.id.video);
        video.setOnClickListener(this);

        find = (LinearLayout) findViewById(R.id.find);
        find.setOnClickListener(this);

    }

    /**
     * 从arrays.xml中取出数据，装入list<T>中
     *
     * @return
     */
    private List<MainDrawerMenu> getMenuItem() {
        List<MainDrawerMenu> list_menu = new ArrayList<MainDrawerMenu>();

        String[] itemTitle = getResources().getStringArray(R.array.item_title);
        TypedArray itemIconRes = getResources().obtainTypedArray(R.array.item_icon_res);

        for (int i = 0; i < itemTitle.length; i++) {

            MainDrawerMenu lmi = new MainDrawerMenu();
            lmi.setMainDrawer_icon(itemIconRes.getResourceId(i, 0));
            lmi.setMainDrawer_menuName(itemTitle[i]);
            list_menu.add(lmi);
        }
        return list_menu;
    }

    private void setDefaultFragment() {
        newFragment = NewFragment.getInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_content, newFragment);
        currentFragment = newFragment;
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news:
                if (newFragment == null) {
                    newFragment = NewFragment.getInstance();
                }
                // 使用当前Fragment的布局替代id_content的控件
                switchContent(currentFragment, newFragment);
                break;
            case R.id.read:
                if (readFragment == null) {
                    readFragment = ReadFragment.getInstance();
                }
                // 使用当前Fragment的布局替代id_content的控件
                switchContent(currentFragment, readFragment);
                break;
            case R.id.video:
                if (videoFragment == null) {
                    videoFragment = VideoFragment.getInstance();
                }
                // 使用当前Fragment的布局替代id_content的控件
                switchContent(currentFragment, videoFragment);
                break;
            case R.id.find:
                if (findFragment == null) {
                    findFragment = FindFragment.getInstance();
                }
                // 使用当前Fragment的布局替代id_content的控件
                switchContent(currentFragment, findFragment);
                break;
            case R.id.icon_view:
                Toast.makeText(MainActivity.this,"登录",Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_me:
                Toast.makeText(MainActivity.this,"关于我",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(MainActivity.this,"设置",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(MainActivity.this,String.valueOf(position) +"  "+ String.valueOf(id),Toast.LENGTH_SHORT).show();
    }


    /**
     * 当fragment进行切换时，采用隐藏与显示的方法加载fragment以防止数据的重复加载
     *
     * @param from
     * @param to
     */
    public void switchContent(Fragment from, Fragment to) {

        if (currentFragment != to) {
            currentFragment = to;
            FragmentManager fm = getSupportFragmentManager();
            //添加渐隐渐现的动画
            FragmentTransaction ft = fm.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                ft.hide(from).add(R.id.frame_content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                ft.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
//                ft.replace(R.id.frame_content,to).commit();
            }
        }
    }


}
