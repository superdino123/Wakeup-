package com.example.administrator.demo.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.config.Const;
import com.example.administrator.demo.ui.login.LoginActivity;
import com.example.administrator.demo.util.PreferencesUtils;
import com.example.administrator.demo.util.SysUtils;
import com.example.administrator.demo.util.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */
public class GuidActivity extends Activity {

    private TextView tv_ignore;
    private ViewPager mViewPager;	//
    private List<View> views;		//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //检查当前软件的版本
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(android.R.color.transparent);//通知栏所需颜色
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        initView();

    }

    private void initView() {
        tv_ignore= (TextView) findViewById(R.id.tv_ignore);
        tv_ignore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
        mViewPager = (ViewPager) findViewById(R.id.vPager);
        views = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        for(int i=0;i<3;i++){
            View view=inflater.inflate(R.layout.activity_guid_item_view, null);
            RelativeLayout rl_guid=(RelativeLayout) view.findViewById(R.id.rl_guid);
            TextView tv_guid= (TextView) view.findViewById(R.id.tv_guid);
            switch (i) {
                case 0:
                    tv_guid.setText("聊天 调侃 听歌");
                    rl_guid.setBackgroundResource(R.drawable.welcome_01);
                    break;
                case 1:
                    tv_guid.setText("天气 星座 笑话");
                    rl_guid.setBackgroundResource(R.drawable.welcome_02);
                    break;
                case 2:
                    tv_guid.setText("小Q 您的娱乐伴侣");
                    rl_guid.setBackgroundResource(R.drawable.welcome_03);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finishActivity();
                        }
                    });
                    break;
            }
            views.add(view);
        }

        mViewPager.setAdapter(new MyViewPagerAdapter(views));
        mViewPager.setCurrentItem(0);

    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private List<View> mListViews;

        public MyViewPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mListViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListViews.get(position), 0);
            return mListViews.get(position);
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }

    void finishActivity(){
        PreferencesUtils.putSharePre(GuidActivity.this, Const.VERSION_CODE, SysUtils.getVersionCode(GuidActivity.this));//保存当前版本号
        if(SysUtils.isLogin(GuidActivity.this)){//已登录
            SysUtils.startActivity(GuidActivity.this, MainActivity.class);//已登录进入首页
        }else{
            SysUtils.startActivity(GuidActivity.this, LoginActivity.class);
        }
        finish();
    }

}