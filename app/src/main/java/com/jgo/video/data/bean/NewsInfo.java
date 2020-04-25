package com.jgo.video.data.bean;

/**
 * Created by ke-oh on 2020-03-20.
 */
public class NewsInfo {

    private int position;
    private String title;
    private String content;

    public NewsInfo(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
