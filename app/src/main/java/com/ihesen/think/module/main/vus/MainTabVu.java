package com.ihesen.think.module.main.vus;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihesen.think.R;
import com.ihesen.think.module.main.FragmentFactory;
import com.ihesen.think.module.main.activities.MainTabActivity;
import com.ihesen.think.module.mvp.vus.Vu;
import com.viewpagerindicator.TabPageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author: ihesen on 2016/4/19 15:43
 * email: hesen@ichsy.com
 */
public class MainTabVu implements Vu {

    private String[] TAB_MENUS = new String[]{"首页", "动态", "我的"};

    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.indicator)
    TabPageIndicator indicator;
    private View rootView;

    @Override
    public View getView() {
        return rootView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.main_tab_vu, viewGroup, false);
        ButterKnife.bind(this, rootView);
        initIndicator();
    }

    private void initIndicator() {
        pager.setAdapter(new TabMenuAdapter(((MainTabActivity) rootView.getContext()).fragmentManager));
        indicator.setViewPager(pager);
        setMainTabTitleText(TAB_MENUS[0]);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setMainTabTitleText(TAB_MENUS[position]);
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
            return TAB_MENUS[position % TAB_MENUS.length].toUpperCase();
        }

        @Override
        public int getCount() {
            return TAB_MENUS.length;
        }
    }

    /**
     * 设置toolbar标题
     *
     * @param text
     */
    public void setMainTabTitleText(String text) {
        ((MainTabActivity) rootView.getContext()).v.setToolbarText(text);
    }
}