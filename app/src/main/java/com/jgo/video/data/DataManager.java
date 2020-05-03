package com.jgo.video.data;

import android.content.Context;

import com.jgo.video.R;
import com.jgo.video.data.bean.ImageInfo;
import com.jgo.video.data.bean.NewsInfo;
import com.jgo.video.data.bean.ScheduleInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ke-oh on 2020/04/05.
 */
public class DataManager {

    public static List<NewsInfo> getNewsList(Context context) {
        List<NewsInfo> newsList = new ArrayList<>();
        NewsInfo newsInfo1 = new NewsInfo(context.getString(R.string.dummy_news_title_1), context.getString(R.string.dummy_news_content_1));
        newsList.add(newsInfo1);

        NewsInfo newsInfo2 = new NewsInfo(context.getString(R.string.dummy_news_title_1), context.getString(R.string.dummy_news_content_1));
        newsList.add(newsInfo2);

        NewsInfo newsInfo3 = new NewsInfo(context.getString(R.string.dummy_news_title_1), context.getString(R.string.dummy_news_content_1));
        newsList.add(newsInfo3);

        NewsInfo newsInfo4 = new NewsInfo(context.getString(R.string.dummy_news_title_1), context.getString(R.string.dummy_news_content_1));
        newsList.add(newsInfo4);

        NewsInfo newsInfo5 = new NewsInfo(context.getString(R.string.dummy_news_title_1), context.getString(R.string.dummy_news_content_1));
        newsList.add(newsInfo5);

        NewsInfo newsInfo6 = new NewsInfo(context.getString(R.string.dummy_news_title_1), context.getString(R.string.dummy_news_content_1));
        newsList.add(newsInfo6);

        NewsInfo newsInfo7 = new NewsInfo(context.getString(R.string.dummy_news_title_1), context.getString(R.string.dummy_news_content_1));
        newsList.add(newsInfo7);

        NewsInfo newsInfo8 = new NewsInfo(context.getString(R.string.dummy_news_title_1), context.getString(R.string.dummy_news_content_1));
        newsList.add(newsInfo8);

        NewsInfo newsInfo9 = new NewsInfo(context.getString(R.string.dummy_news_title_1), context.getString(R.string.dummy_news_content_1));
        newsList.add(newsInfo9);

        return newsList;
    }

    public static List<ImageInfo> getImageList(Context context) {

        List<ImageInfo> imagesList = new ArrayList<>();
        //TODO
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_1.jpg", 400));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_2.jpg", 800));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_3.jpg", 350));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_4.jpg", 400));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_5.jpg", 450));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_6.jpg", 800));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_7.jpg", 350));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_8.jpg", 400));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_9.jpg", 900));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_10.jpg", 900));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_11.jpg", 800));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_12.jpg", 350));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_13.jpg", 350));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_14.jpg", 350));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_15.jpg", 350));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_16.jpg", 800));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_17.jpg", 700));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_18.jpg", 400));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_19.jpg", 600));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_20.jpg", 800));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_21.jpg", 650));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_22.jpg", 350));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_23.jpg", 350));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_24.jpg", 800));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_25.jpg", 400));
        imagesList.add(new ImageInfo("file:///android_asset/images/photo_26.jpg", 550));

        return imagesList;
    }


    public static List<ScheduleInfo> getScheduleList(Context context) {

        List<ScheduleInfo> scheduleList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            scheduleList.add(new ScheduleInfo("Lakers", "Mavericks", new Date()));
        }
        return scheduleList;
    }
}
