package com.jgo.video.ui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.jgo.video.R;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * Created by ke-oh on 2020/04/29.
 */
public class AnimateTextView extends androidx.appcompat.widget.AppCompatTextView {

    private final static String TAG = AnimateTextView.class.getSimpleName();
    protected TextPaint mTextPaint;
    protected Path mDst = new Path(), mPaintPath = new Path();
    protected Path mFontPath = new Path();
    protected float mTextWidth = 0, mTextHeight = 0;
    private String mText;
    protected PathMeasure mPathMeasure = new PathMeasure();
    private Paint mDrawPaint;
    private float mLength = 0;
    private float mCurrentValue;

    protected ValueAnimator mAnimator;

    public AnimateTextView(Context context) {
        super(context);
        initPaint();
        initPath();
    }

    public AnimateTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initPath();
    }

    public AnimateTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initPath();
    }

    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        int hSpeSize = MeasureSpec.getSize(heightMeasureSpec);
//        int hSpeMode = MeasureSpec.getMode(heightMeasureSpec);
//        int wSpeSize = MeasureSpec.getSize(widthMeasureSpec);
//        int wSpeMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

//        mTextWidth = TextUtil.getTextWidth(mTextPaint,mText);

        if (mTextWidth > width) {
            mTextWidth = width;
        }

        setMeasuredDimension(width, height);
    }*/

    protected void initPaint() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mTextPaint = new TextPaint();
        mTextPaint.setTextSize(20);

        mDrawPaint = new Paint();
        mDrawPaint.setAntiAlias(true);
        mDrawPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryOrange));
        mDrawPaint.setStrokeWidth(5);
        mDrawPaint.setStyle(Paint.Style.STROKE);
        mDrawPaint.setTextSize(50);
//        TextPaint textPaint = new TextPaint(mTextPaint);
//        mTextPaint.setTypeface();

        /*mTextPaint.setTextSize(mTextSize);

        if (mTextInCenter) {
            mDrawPaint.setTextAlign(Paint.Align.CENTER);
        }
        if (mTypeface != null) {
            mTextPaint.setTypeface(mTypeface);
        }*/

    }

    protected void initPath() {
        mDst.reset();
        mFontPath.reset();

        mText = String.valueOf(getText());
        if (!TextUtils.isEmpty(getText())) {
            //获取宽高
            mTextWidth = Layout.getDesiredWidth(mText, mTextPaint);
            Paint.FontMetrics metrics = mTextPaint.getFontMetrics();
            mTextHeight = metrics.bottom - metrics.top;

            mTextPaint.getTextPath(mText, 0, mText.length(), 0f, -metrics.ascent, mFontPath);
            mPathMeasure.setPath(mFontPath, false);
            mLength = mPathMeasure.getLength();
        }
    }

    public void startAnimator(float start, float end) {
        mAnimator = ValueAnimator.ofFloat(start, end);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float percent = (float) valueAnimator.getAnimatedValue();
                Log.d(TAG, "percent : " + percent);
                drawPath(percent);
                //postInvalidate();
            }
        });

        mAnimator.setDuration(3000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.start();
        /*if (animationStyle == RESTART) {
            mAnimator.setRepeatMode(ValueAnimator.RESTART);
            mAnimator.setRepeatCount(repeatCount);
        } else if (animationStyle == REVERSE) {
            mAnimator.setRepeatMode(ValueAnimator.REVERSE);
            mAnimator.setRepeatCount(repeatCount);
        }*/
    }

    public void drawPath(float end) {
        //重置路径
        mPathMeasure.setPath(mFontPath, false);
        mDst.reset();
        mPaintPath.reset();

        boolean hasMore = true;
        while (hasMore) {
            mLength = mPathMeasure.getLength();
            //mStartValue = mLength * mStart;
            mCurrentValue = mLength * end;
            Log.d(TAG, "drawPath: length:" + mLength + ", mCurrentValue : " + mCurrentValue);
            //Log.d(TAG, "drawPath mStartValue : " + mStartValue + ", mEndValue:" + mEndValue + ", mLength : " + mLength);
            //Log.d(TAG, "drawPath: mLength:" + mLength);
            //Log.d(TAG, "drawPath: close? " + mPathMeasure.isClosed());
            mPathMeasure.getSegment(0, mCurrentValue, mDst, true);

            //绘画画笔效果
            /*if (showPainterActually) {
                mPathMeasure.getPosTan(mEndValue, mCurPos, null);
                drawPaintPath(mCurPos[0], mCurPos[1], mPaintPath);
            }*/
            hasMore = mPathMeasure.nextContour();
        }

        //绘画路径
        postInvalidate();
        //invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw");
        //canvas.drawPath(mDst, mDrawPaint);
    }
}
