package com.example.administrator.wechatsample_guolin;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.ViewConfiguration;
import android.view.Window;

import com.astuetz.PagerSlidingTabStrip;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends FragmentActivity {

    private ChatFragment chatFragment;
    private FoundFragment foundFragment;
    private ContactsFragment contactsFragment;
    private PagerSlidingTabStrip tabs;

    //获取当前屏幕的密度
    private DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOverflowShowingAlways();
        displayMetrics = getResources().getDisplayMetrics();
        ViewPager pager = (ViewPager) findViewById(R.id.paper);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        setTabsValue();
        pager.setAdapter(new MyPaperAdapter(getSupportFragmentManager()));
        tabs.setViewPager(pager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }


    /**
     * 三个界面的适配器,
     */
    public class MyPaperAdapter extends FragmentPagerAdapter {

        public MyPaperAdapter(FragmentManager fm) {
            super(fm);
        }
        private final String[] titles = {"聊天", "发现", "通讯录"};


        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (chatFragment == null) {
                        chatFragment = new ChatFragment();
                    }
                    return chatFragment;
                case 1:
                    if (foundFragment == null) {
                        foundFragment = new FoundFragment();
                    }
                    return foundFragment;
                case 2:
                    if (contactsFragment == null) {
                        contactsFragment = new ContactsFragment();
                    }
                    return contactsFragment;
                default:
                    return null;
            }

        }


    }


    //------------------------这个设置方法----------------


    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值
     */
    private void setTabsValue() {
        //设置Tab是自动填满屏幕的
        tabs.setShouldExpand(true);
        //设置Tab的分界线是透明的
        tabs.setDividerColor(Color.TRANSPARENT);
        //设置Tab底部线的高度
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, displayMetrics));
        //设置Tab Inddicator的高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, displayMetrics));
        tabs.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, displayMetrics));
        tabs.setIndicatorColor(Color.parseColor("#45c01a"));
        //
        tabs.setTabBackground(0);
    }

}
