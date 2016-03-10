package com.xunmeng.jpush.ui;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.xunmeng.jpush.R;
import com.xunmeng.jpush.adapter.LeftAdapter;
import com.xunmeng.jpush.app.MY;
import com.xunmeng.jpush.entity.MainDrawerMenu;
import com.xunmeng.jpush.fragment.FindFragment;
import com.xunmeng.jpush.fragment.NewFragment;
import com.xunmeng.jpush.fragment.ReadFragment;
import com.xunmeng.jpush.fragment.VideoFragment;
import com.xunmeng.jpush.utils.LogUtils;

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
    private ImageView new_img;
    private ImageView read_icon;
    private ImageView video_icon;
    private ImageView find_icon;
    private final int newsFlag = 0;
    private final int readFlag = 1;
    private final int videoFlag = 2;
    private final int findFlag = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initUi();
        setDefaultFragment();
        MY.getAppInstance().addActivity(this);

    }

    private void initUi() {
        initToolbar();
        head_icon = (ImageView) findViewById(R.id.icon_view);
        head_icon.setOnClickListener(this);

        list_found = (ListView) findViewById(R.id.lv_main_drawer_leftmenu);
        list_found.setAdapter(new LeftAdapter(this, getMenuItem()));
        list_found.setOnItemClickListener(this);

        about_me = (LinearLayout) findViewById(R.id.about_me);
        about_me.setOnClickListener(this);

        setting = (LinearLayout) findViewById(R.id.setting);
        setting.setOnClickListener(this);

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tb_main);
        toolbar.setTitle("天道酬勤");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
//        toolbar.setLogo(R.drawable.);
//        toolbar.setLogo(R.drawable.ic_launcher); //设置logo
//        toolbar.setBackgroundColor(getResources().getColor(R.color.grey)); //设置背景颜色
//        toolbar.setSubtitle("副标题"); //设置副标题

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_list_white);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        news = (LinearLayout) findViewById(R.id.news);
        news.setOnClickListener(this);

        new_img = (ImageView) findViewById(R.id.news_icon);
//        new_img.setOnClickListener(this);

        read = (LinearLayout) findViewById(R.id.read);
        read.setOnClickListener(this);

        read_icon = (ImageView) findViewById(R.id.read_icon);
//        read_icon.setOnClickListener(this);


        video = (LinearLayout) findViewById(R.id.video);
        video.setOnClickListener(this);

        video_icon = (ImageView) findViewById(R.id.video_icon);
//        video_icon.setOnClickListener(this);

        find = (LinearLayout) findViewById(R.id.find);
        find.setOnClickListener(this);

        find_icon = (ImageView) findViewById(R.id.find_icon);
//        find_icon.setOnClickListener(this);
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
        new_img.setImageResource(R.drawable.news_blue);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news:
                select(newsFlag);
                break;
            case R.id.read:
                select(readFlag);
                break;
            case R.id.video:
                select(videoFlag);
                break;
            case R.id.find:
                select(findFlag);
                break;
            case R.id.icon_view:
                Intent LoginIntent = new Intent(this, LoginActivity.class);
                this.startActivity(LoginIntent);
                LogUtils.e("icon_view");
                Toast.makeText(MainActivity.this, "登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_me:
                Intent aboutMeIntent = new Intent(this, AboutMeActivity.class);
                this.startActivity(aboutMeIntent);
                LogUtils.e("about_me");
                break;
            case R.id.setting:
                LogUtils.e("setting");
                Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public void select(int i) {
        reFreshUi();
        switch (i) {
            case newsFlag:
                if (newFragment == null) {
                    newFragment = NewFragment.getInstance();
                }
                // 使用当前Fragment的布局替代id_content的控件
                switchContent(currentFragment, newFragment);
                new_img.setImageResource(R.drawable.news_blue);
                break;
            case readFlag:
                if (readFragment == null) {
                    readFragment = ReadFragment.getInstance();
                }
                // 使用当前Fragment的布局替代id_content的控件
                switchContent(currentFragment, readFragment);
                read_icon.setImageResource(R.drawable.read_select);
                break;
            case videoFlag:
                if (videoFragment == null) {
                    videoFragment = VideoFragment.getInstance();
                }
                // 使用当前Fragment的布局替代id_content的控件
                switchContent(currentFragment, videoFragment);
                video_icon.setImageResource(R.drawable.news_blue);
                break;
            case findFlag:
                if (findFragment == null) {
                    findFragment = FindFragment.getInstance();
                }
                // 使用当前Fragment的布局替代id_content的控件
                switchContent(currentFragment, findFragment);
                find_icon.setImageResource(R.drawable.news_blue);
                break;
            default:
                break;
        }
    }

    private void reFreshUi() {
        new_img.setImageResource(R.drawable.news_gray);
        read_icon.setImageResource(R.drawable.read_unselect);
        video_icon.setImageResource(R.drawable.news_gray);
        find_icon.setImageResource(R.drawable.news_gray);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(MainActivity.this, String.valueOf(position) + "  " + String.valueOf(id), Toast.LENGTH_SHORT).show();
        switch (position) {
            case 0:
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                break;
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
//            super.onBackPressed();
            Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        }
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
