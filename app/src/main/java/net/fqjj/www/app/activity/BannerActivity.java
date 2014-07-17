package net.fqjj.www.app.activity;

import android.app.Application;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import net.fqjj.www.app.R;
import net.fqjj.www.app.adapter.MyPagerAdapter;

import java.util.ArrayList;


public class BannerActivity extends ActionBarActivity {

    private Application application;

    private ViewPager viewPager;

    private int[] imglist = new int[]{
            R.drawable.user_lead_01,
            R.drawable.user_lead_02,
            R.drawable.user_lead_03
    };

    // 导航点容器
    private LinearLayout pointContainer;
    private ImageView currentActiveNode = null;


    ArrayList<RelativeLayout> list = new ArrayList<RelativeLayout>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        pointContainer = (LinearLayout) findViewById(R.id.point_container);

        initBanner();
    }

    private void initBanner() {
        
        int size = imglist.length;
        
        for (int i = 0; i < size; i++) {
            RelativeLayout layout = (RelativeLayout) getLayoutInflater().inflate(R.layout.flip_item, null);
            ImageView flipBg = (ImageView) layout.findViewById(R.id.flip_bg);
            flipBg.setBackgroundResource(imglist[i]);
            flipBg.setScaleType(ImageView.ScaleType.FIT_CENTER);
            list.add(layout);

            // 最后一页可点击
            if (i == imglist.length - 1) {
                layout.setFocusable(true);
                layout.setClickable(true);
                layout.setOnClickListener(layoutListener);
            }

            // 设置导航点
            ImageView img = new ImageView(this);
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            img.setLayoutParams(layoutParams);

            if (i == 0) {
                img.setImageResource(R.drawable.navigator_icon_hover);
                currentActiveNode = img;
            } else {
                img.setImageResource(R.drawable.navigator_icon_normal);
            }

            pointContainer.addView(img);
        }

        MyPagerAdapter adapter = new MyPagerAdapter(list);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(pageChangeListener);
    }

    View.OnClickListener layoutListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), Main.class));
            finish();
        }
    };


    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.d("onPageScrolled", "onPageScrolled");
        }

        @Override
        public void onPageSelected(int position) {
            //TODO 此处有一个BUG，第一个滑动点无法还原到normal
            currentActiveNode.setBackgroundResource(R.drawable.navigator_icon_normal);
            ImageView view = (ImageView) pointContainer.getChildAt(position);
            view.setBackgroundResource(R.drawable.navigator_icon_hover);
            currentActiveNode = view;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d("onPageScrollStateChanged", "onPageScrollStateChanged");
        }
    };


}
