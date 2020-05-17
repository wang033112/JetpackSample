package com.jgo.video.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jgo.video.R;

import androidx.annotation.Nullable;

/**
 * Created by ke-oh on 2020/05/16.
 */
public class ScrollScoreView extends LinearLayout {

    private Context mContext;
    private TextView mBeforeText;
    private TextView mAfterText;
    private int mWidth;
    private int mHeight;
    private int mCurrentScroll;
    private int mCurrentText = 1;


    private static final int LAYOUT_TYPE_INIT    = 1;
    private static final int LAYOUT_TYPE_ANIMATE = 2;
    private static final int LAYOUT_TYPE_SWITCH  = 3;
    private int mLayoutType = LAYOUT_TYPE_INIT;

    public ScrollScoreView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public ScrollScoreView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public ScrollScoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        mBeforeText = new TextView(mContext);
        mBeforeText.setText(String.valueOf(mCurrentText));
        mBeforeText.setGravity(Gravity.CENTER);
        mBeforeText.setTextSize(32);
        mBeforeText.setTextColor(mContext.getColor(R.color.gray));
        addView(mBeforeText);

        mAfterText = new TextView(mContext);
        mAfterText.setText("");
        mAfterText.setGravity(Gravity.CENTER);
        mAfterText.setTextSize(32);
        mAfterText.setTextColor(mContext.getColor(R.color.gray));
        addView(mAfterText);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mBeforeText.setHeight(mHeight);
        mAfterText.setHeight(mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        switch (mLayoutType) {
            case LAYOUT_TYPE_INIT :
            case LAYOUT_TYPE_SWITCH:
                mBeforeText.layout(0, 0, mWidth, mHeight);
                mAfterText.layout(0, mHeight, mWidth, mHeight * 2);
                break;
            case LAYOUT_TYPE_ANIMATE :
                mBeforeText.layout(0, -mCurrentScroll, mWidth, mHeight - mCurrentScroll);
                mAfterText.layout(0, mHeight - mCurrentScroll, mWidth, mHeight * 2 - mCurrentScroll);
                break;
        }
    }

    public void changeTextTo(final String text) {
        ValueAnimator animator = ObjectAnimator.ofInt(0, mHeight);
        animator.addUpdateListener((updateValue) -> {
            mCurrentScroll = (int) updateValue.getAnimatedValue();
            mLayoutType = LAYOUT_TYPE_ANIMATE;
            requestLayout();
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                postDelayed(() -> {
                    //Change inference
                    TextView tempText = mAfterText;
                    mAfterText = mBeforeText;
                    mBeforeText = tempText;

                    mLayoutType = LAYOUT_TYPE_SWITCH;
                    mAfterText.setText("");
                    LinearLayout.LayoutParams afterLP = (LinearLayout.LayoutParams) mAfterText.getLayoutParams();
                    afterLP.topMargin = mHeight;
                    mAfterText.setLayoutParams(afterLP);
                }, 100);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mAfterText.setText(text);
            }
        });

        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(200);
        animator.start();
    }
}
