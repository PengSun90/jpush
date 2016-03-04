package com.xunmeng.jpush.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.xunmeng.jpush.R;
import com.xunmeng.jpush.fragment.NewFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Fragment isFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

//        setDefaultFragment();
    }


    private void setDefaultFragment()
    {
        Fragment newFragment = new NewFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_content, newFragment);
//        fragmentTransaction.show(newFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v)
    {
        FragmentManager fm = getSupportFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();

//        switch (v.getId())
//        {
//            case R.id.tab_bottom_weixin:
//                if (mWeixin == null)
//                {
//                    mWeixin = new ContentFragment();
//                }
//                // 使用当前Fragment的布局替代id_content的控件
//                transaction.replace(R.id.id_content, mWeixin);
//                break;
//            case R.id.tab_bottom_friend:
//                if (mFriend == null)
//                {
//                    mFriend = new FriendFragment();
//                }
//                transaction.replace(R.id.id_content, mFriend);
//                break;
//        }
//        // transaction.addToBackStack();
//        // 事务提交
        transaction.commit();
    }
    /**
     * 当fragment进行切换时，采用隐藏与显示的方法加载fragment以防止数据的重复加载
     * @param from
     * @param to
     */
    public void switchContent(Fragment from, Fragment to) {

        if (isFragment != to) {
            isFragment = to;
            FragmentManager fm = getSupportFragmentManager();
            //添加渐隐渐现的动画
            FragmentTransaction ft = fm.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                ft.hide(from).add(R.id.frame_content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                ft.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
}
