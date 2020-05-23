package com.jgo.video.data.bean;

/**
 * Created by ke-oh on 2020-05-20.
 */
public class VideoInfo {

    private int position;
    private String title;
    private int src;

    public VideoInfo(int src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }
}
