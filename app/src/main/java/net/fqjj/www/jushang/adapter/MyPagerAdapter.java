package net.fqjj.www.jushang.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by joejoe
 */
public class MyPagerAdapter extends PagerAdapter {

    private List<RelativeLayout> lists;


    public MyPagerAdapter(List<RelativeLayout> lists) {
        super();
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    // 初使化
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = lists.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView((View) object);
    }

}