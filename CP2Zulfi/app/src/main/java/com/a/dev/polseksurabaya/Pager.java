package com.a.dev.polseksurabaya;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tab
    int tabCount;


   //contractor to the class
    public Pager(FragmentManager fm, int tabCount){
        super(fm);
        //initial tabcount
        this.tabCount=tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Utama t1=new Utama();
                return t1;
            case 1:
                About t3=new About();
                return t3;
        default:
            return null;
        }
    }


    @Override
    public int getCount() {
        return tabCount;
    }
}
