package com.example.administrator.demo.ui.tab.tab3;


import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.share.PopShareHelper;
import com.example.administrator.demo.share.ShareContent;
import com.example.administrator.demo.ui.base.SlideBackActivity;
import com.example.administrator.demo.util.SysUtils;

/**
 * 关于
 * @author 白玉梁
 * @date 2016-5-12 下午5:07:54
 */
public class AboutActivity extends SlideBackActivity{

    String appname = "";
    int version_now;//当前版本号

    PopShareHelper popShareHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        appname = SysUtils.getAppName(this);
        initTitleBar("我的","关于","分享",this);
        popShareHelper = new PopShareHelper(this);
        initView();
        version_now = Integer.parseInt(SysUtils.getVersionCode(this));//当前版本号
    }

    private void initView() {
        ((TextView) findViewById(R.id.app_name_and_version)).setText(appname + "V" + SysUtils.getAppVersionName(this));
        Linkify.addLinks(((TextView) findViewById(R.id.tv_csdn)),Linkify.ALL);
        Linkify.addLinks(((TextView) findViewById(R.id.tv_git)),Linkify.ALL);
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch (arg0.getId()){
            case R.id.tv_right:
                ShareContent shareContent = new ShareContent("起来嗨", "哈哈哈", "http://www.baidu.com");
                popShareHelper.setShareContent(shareContent);
                popShareHelper.show(tv_right);
                break;
        }
    }
}
