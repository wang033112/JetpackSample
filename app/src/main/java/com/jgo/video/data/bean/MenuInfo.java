package com.jgo.video.data.bean;

/**
 * @author jgo
 */
public class MenuInfo {

    private String title;

    private int iconId;

    public MenuInfo() {
    }

    public MenuInfo(String title, int iconId) {
        this.title = title;
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
