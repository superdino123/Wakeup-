package com.example.administrator.demo.share;

/**
 * Created by Administrator on 2016/12/27.
 */
public class ShareContent {

    public String title; //分享标题
    public String content; //内容
    public String url; //链接

    public ShareContent(String title, String content, String url) {
        this.title = title;
        this.content = content;
        this.url = url;
    }
}
