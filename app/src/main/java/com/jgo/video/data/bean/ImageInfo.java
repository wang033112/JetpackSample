package com.jgo.video.data.bean;

/**
 * Created by ke-oh on 2020-04-18.
 */
public class ImageInfo {

    private int position;
    private String title;
    private String src;
    private int height;

    public ImageInfo(String src, int height) {
        this.src = src;
        this.height = height;
    }

    public ImageInfo(String title, String src) {
        this.title = title;
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getHeight() {
        return height;
    }
}
