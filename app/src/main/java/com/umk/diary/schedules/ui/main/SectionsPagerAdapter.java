package com.umk.diary.schedules.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.umk.diary.R;
import com.umk.diary.schedules.Friday;
import com.umk.diary.schedules.Monday;
import com.umk.diary.schedules.Thursday;
import com.umk.diary.schedules.Tuesday;
import com.umk.diary.schedules.Wednesday;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3,R.string.tab_text_4,R.string.tab_text_5};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment =null;
        switch (position){
            case 0:
                fragment = new Monday();
                break;
            case 1:
                fragment = new Tuesday();
                break;
            case 2:
                fragment = new Wednesday();
                break;
            case 3:
                fragment = new Thursday();
                break;
            case 4:
                fragment = new Friday();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 5 total pages.
        return 5;
    }
}