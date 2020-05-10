package com.jgo.video.ui.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.jgo.video.R;

import androidx.annotation.Nullable;

/**
 * Created by ke-oh on 2020/05/10.
 */
public class LivingLine extends View {

    private Context mContext;
    private float mCurrentValue;

    private Paint mPaint;
    private Path mPath;

    public LivingLine(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public LivingLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public LivingLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mContext.getColor(R.color.colorPrimaryOrange));
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        ValueAnimator animator = ObjectAnimator.ofFloat(0.0f, width);
        animator.addUpdateListener((updateValue) -> {
            mCurrentValue = (float) updateValue.getAnimatedValue();
            invalidate();
        });

        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setDuration(1500);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        if (mPaint.getStrokeWidth() != getHeight()) {
            mPaint.setStrokeWidth(getHeight());
        }
        float mStartX = mCurrentValue - 50;
        float mEndX = mStartX + 100;
        mPath.moveTo(mStartX, getHeight() / 2);
        mPath.lineTo(mEndX, getHeight() / 2);
        canvas.drawPath(mPath, mPaint);
    }
}
