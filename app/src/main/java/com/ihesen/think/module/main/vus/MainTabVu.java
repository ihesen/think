package com.ihesen.think.module.main.vus;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ihesen.think.R;
import com.ihesen.think.module.base.TopBarVu;
import com.ihesen.think.module.main.FragmentFactory;
import com.ihesen.think.module.main.activities.MainTabActivity;
import com.viewpagerindicator.TabPageIndicator;

import butterknife.Bind;

/**
 * author: ihesen on 2016/4/19 15:43
 * email: hesen@ichsy.com
 */
public class MainTabVu extends TopBarVu {

    private String[] TAB_MENUS = new String[]{"首页", "动态", "我的"};

    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.indicator)
    TabPageIndicator indicator;

    @Override
    public int getContentLayoutRes() {
        return R.layout.main_tab_vu;
    }

    @Override
    protected void logic() {
        initIndicator();
    }

    private void initIndicator() {
        pager.setAdapter(new TabMenuAdapter(((MainTabActivity) rootView.getContext()).fragmentManager));
        indicator.setViewPager(pager);
        setToolbarText(TAB_MENUS[0]);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setToolbarText(TAB_MENUS[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class TabMenuAdapter extends FragmentPagerAdapter {
        public TabMenuAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.obtainFragment(position % TAB_MENUS.length);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TAB_MENUS[position % TAB_MENUS.length];
        }

        @Override
        public int getCount() {
            return TAB_MENUS.length;
        }
    }
}