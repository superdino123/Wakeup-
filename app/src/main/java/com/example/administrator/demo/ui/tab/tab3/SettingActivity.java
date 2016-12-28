package com.example.administrator.demo.ui.tab.tab3;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.demo.R;
import com.example.administrator.demo.config.Const;
import com.example.administrator.demo.ui.base.SlideBackActivity;
import com.example.administrator.demo.util.PreferencesUtils;
import com.example.administrator.demo.view.ActionSheetCenterDialog;

/**
 * @author 白玉梁
 */
public class SettingActivity extends SlideBackActivity {
    private RelativeLayout rl_1, rl_2, rl_3;
    TextView tv_1, tv_2;
    RadioButton rb_voice, rb_text;
    CheckBox cb_speech;

    ActionSheetCenterDialog actionSheetCenterDialog1, actionSheetCenterDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initTitleBar("我的", "设置", "", this);
        initView();
        initData();
    }

    private void initView() {
        rl_1 = (RelativeLayout) findViewById(R.id.rl_1);//聊天记录
        rl_2 = (RelativeLayout) findViewById(R.id.rl_2);//录音设置
        rl_3 = (RelativeLayout) findViewById(R.id.rl_3);//朗读语言

        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_2 = (TextView) findViewById(R.id.tv_2);

        rb_voice = (RadioButton) findViewById(R.id.rb_voice);
        rb_text = (RadioButton) findViewById(R.id.rb_text);
        cb_speech= (CheckBox) findViewById(R.id.cb_speech);

        rl_1.setOnClickListener(this);
        rl_2.setOnClickListener(this);
        rl_3.setOnClickListener(this);
        rb_voice.setOnClickListener(this);
        rb_text.setOnClickListener(this);
        cb_speech.setOnClickListener(this);
    }

    private void initData() {
        String str1 = PreferencesUtils.getSharePreStr(this, Const.XF_SET_VOICE_RECORD);
        String str2 = PreferencesUtils.getSharePreStr(this, Const.XF_SET_VOICE_READ);
        if (TextUtils.isEmpty(str1)) {
            tv_1.setText("录音语言：mandarin");
        } else {
            tv_1.setText("录音语言：" + str1);
        }
        if (TextUtils.isEmpty(str2)) {
            tv_2.setText("朗读语言：xiaoyu");
        } else {
            tv_2.setText("朗读语言：" + str2);
        }
        String str3 = PreferencesUtils.getSharePreStr(this, Const.IM_VOICE_TPPE);
        String str4 = PreferencesUtils.getSharePreStr(this, Const.IM_SPEECH_TPPE);
        if(!TextUtils.isEmpty(str3)&&str3.equals("1")){
            rb_voice.setChecked(true);
        }else{
            rb_text.setChecked(true);
        }
        if(!TextUtils.isEmpty(str4)&&str4.equals("1")){
            cb_speech.setChecked(true);
        }else{
            cb_speech.setChecked(false);
        }
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch (arg0.getId()) {//返回
            case R.id.rl_1:
               // SysUtils.startActivity(this, MsgHistoryActivity.class);
                break;
            case R.id.rl_2:
                actionSheetCenterDialog1 = new ActionSheetCenterDialog(this)
                        .builder()
                        .addSheetItem("mandarin(普通话)", ActionSheetCenterDialog.SheetItemColor.Blue, onSheetItemClickListener1)
                        .addSheetItem("cantonese(粤语)", ActionSheetCenterDialog.SheetItemColor.Blue, onSheetItemClickListener1)
                        .addSheetItem("henanese(河南话)", ActionSheetCenterDialog.SheetItemColor.Blue, onSheetItemClickListener1)
                        .addSheetItem("en_us(英语)", ActionSheetCenterDialog.SheetItemColor.Blue, onSheetItemClickListener1)
                ;
                actionSheetCenterDialog1.show();
                break;
            case R.id.rl_3:
                actionSheetCenterDialog2 = new ActionSheetCenterDialog(this)
                        .builder()
                        .addSheetItem("xiaoyu(男普通话)", ActionSheetCenterDialog.SheetItemColor.Blue, onSheetItemClickListener2)
                        .addSheetItem("xiaoyan(女普通话)", ActionSheetCenterDialog.SheetItemColor.Blue, onSheetItemClickListener2)
                        .addSheetItem("xiaomei(女粤语)", ActionSheetCenterDialog.SheetItemColor.Blue, onSheetItemClickListener2)
                        .addSheetItem("xiaolin(女台湾话)", ActionSheetCenterDialog.SheetItemColor.Blue, onSheetItemClickListener2)
                        .addSheetItem("xiaorong(女四川话)", ActionSheetCenterDialog.SheetItemColor.Blue, onSheetItemClickListener2)
                        .addSheetItem("xiaokun(男河南话)", ActionSheetCenterDialog.SheetItemColor.Blue, onSheetItemClickListener2);
                actionSheetCenterDialog2.show();
                break;
            case R.id.rb_voice:
                PreferencesUtils.putSharePre(this, Const.IM_VOICE_TPPE, "1");//以语音形式发送
                break;
            case R.id.rb_text:
                PreferencesUtils.putSharePre(this, Const.IM_VOICE_TPPE, "2");//已文本形式发送
                break;
            case R.id.cb_speech:
                if(cb_speech.isChecked()){
                    PreferencesUtils.putSharePre(this, Const.IM_SPEECH_TPPE, "1");//回复内容直接朗读
                }else{
                    PreferencesUtils.putSharePre(this, Const.IM_SPEECH_TPPE, "0");
                }
                break;
        }
    }

    ActionSheetCenterDialog.OnSheetItemClickListener onSheetItemClickListener1 = new ActionSheetCenterDialog.OnSheetItemClickListener() {
        @Override
        public void onClick(int which) {
            switch (which) {
                case 1:
                    tv_1.setText("录音语言：mandarin");
                    PreferencesUtils.putSharePre(SettingActivity.this, Const.XF_SET_VOICE_RECORD, "mandarin");
                    break;
                case 2:
                    tv_1.setText("录音语言：cantonese");
                    PreferencesUtils.putSharePre(SettingActivity.this, Const.XF_SET_VOICE_RECORD, "cantonese");
                    break;
                case 3:
                    tv_1.setText("录音语言：henanese");
                    PreferencesUtils.putSharePre(SettingActivity.this, Const.XF_SET_VOICE_RECORD, "henanese");
                    break;
                case 4:
                    tv_1.setText("录音语言：en_us");
                    PreferencesUtils.putSharePre(SettingActivity.this, Const.XF_SET_VOICE_RECORD, "en_us");
                    break;
            }
        }
    };

    ActionSheetCenterDialog.OnSheetItemClickListener onSheetItemClickListener2 = new ActionSheetCenterDialog.OnSheetItemClickListener() {
        @Override
        public void onClick(int which) {
            switch (which) {
                case 1:
                    tv_2.setText("朗读语言：xiaoyu");
                    PreferencesUtils.putSharePre(SettingActivity.this, Const.XF_SET_VOICE_READ, "xiaoyu");
                    break;
                case 2:
                    tv_2.setText("朗读语言：xiaoyan");
                    PreferencesUtils.putSharePre(SettingActivity.this, Const.XF_SET_VOICE_READ, "xiaoyan");
                    break;
                case 3:
                    tv_2.setText("朗读语言：xiaomei");
                    PreferencesUtils.putSharePre(SettingActivity.this, Const.XF_SET_VOICE_READ, "xiaomei");
                    break;
                case 4:
                    tv_2.setText("朗读语言：xiaolin");
                    PreferencesUtils.putSharePre(SettingActivity.this, Const.XF_SET_VOICE_READ, "xiaolin");
                    break;
                case 5:
                    tv_2.setText("朗读语言：xiaorong");
                    PreferencesUtils.putSharePre(SettingActivity.this, Const.XF_SET_VOICE_READ, "xiaorong");
                    break;
                case 6:
                    tv_2.setText("朗读语言：xiaokun");
                    PreferencesUtils.putSharePre(SettingActivity.this, Const.XF_SET_VOICE_READ, "xiaokun");
                    break;
            }
        }
    };

}
