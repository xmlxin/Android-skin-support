package com.ximsfei.skindemo;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ximsfei.skindemo.tab.FirstFragment;
import com.ximsfei.skindemo.tab.LastFragment;
import com.ximsfei.skindemo.tab.MiddleFragment;
import com.ximsfei.skindemo.tab.TabFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import skin.support.SkinCompatManager;

/**
 * Created by ximsfei on 2017/1/9.
 */

public class MainActivity extends BaseActivity {
//public class MainActivity extends AppCompatActivity {
    private TabFragmentPagerAdapter mTabFragmentPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        configFragments();
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(SkinCompatManager.get(MainActivity.this).getCurSkinName())) {
                    SkinCompatManager.get(MainActivity.this).loadSkin("night.skin", null);
                } else {
                    SkinCompatManager.get(MainActivity.this).restoreDefaultTheme();
                }
            }
        });
    }

    private void configFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(new FirstFragment());
        list.add(new MiddleFragment());
        list.add(new LastFragment());
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager(), list));
        List<String> listTitle = new ArrayList<>();
        listTitle.add("系统组件");
        listTitle.add("自定义View");
        listTitle.add("第三方库控件");
        mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), list, listTitle);
        viewPager.setAdapter(mTabFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setWindowStatusBarColor() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(android.R.color.darker_gray));
                //window.setNavigationBarColor(color);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearWindowStatusBarColor() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
