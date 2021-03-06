package com.jgo.video.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.jgo.video.R;

import androidx.core.content.ContextCompat;

/**
 * Created by ke-oh on 2020/05/02.
 */
public class ShadowLayout extends FrameLayout {

    private int mShadowColor;

    private float mShadowLimit;

    private float mCornerRadius;

    private float mDx;

    private float mDy;

    private boolean leftShow;

    private boolean rightShow;

    private boolean topShow;

    private boolean bottomShow;


    private boolean mInvalidateShadowOnSizeChanged = true;
    private boolean mForceInvalidateShadow = false;

    public ShadowLayout(Context context) {
        super(context);
        initView(context, null);
    }

    public ShadowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ShadowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return 0;
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return 0;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0 && (getBackground() == null || mInvalidateShadowOnSizeChanged
                || mForceInvalidateShadow)) {
            mForceInvalidateShadow = false;
            setBackgroundCompat(w, h);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mForceInvalidateShadow) {
            mForceInvalidateShadow = false;
            setBackgroundCompat(right - left, bottom - top);
        }
    }

    public void setInvalidateShadowOnSizeChanged(boolean invalidateShadowOnSizeChanged) {
        mInvalidateShadowOnSizeChanged = invalidateShadowOnSizeChanged;
    }

    public void invalidateShadow() {
        mForceInvalidateShadow = true;
        requestLayout();
        invalidate();
    }

    private void initView(Context context, AttributeSet attrs) {
        initAttributes(context, attrs);

        int xPadding = (int) (mShadowLimit + Math.abs(mDx));
        int yPadding = (int) (mShadowLimit + Math.abs(mDy));
        int left;
        int right;
        int top;
        int bottom;
        if (leftShow) {
            left = xPadding;
        } else {
            left = 0;
        }

        if (topShow) {
            top = yPadding;
        } else {
            top = 0;
        }


        if (rightShow) {
            right = xPadding;
        } else {
            right = 0;
        }

        if (bottomShow) {
            bottom = yPadding;
        } else {
            bottom = 0;
        }

        setPadding(left, top, right, bottom);
    }

    private void setBackgroundCompat(int w, int h) {
        Bitmap bitmap = createShadowBitmap(w, h, mCornerRadius, mShadowLimit, mDx,
                mDy, mShadowColor, Color.TRANSPARENT);
        BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
        setBackground(drawable);
    }


    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray attr = getTypedArray(context, attrs, R.styleable.ShadowLayout);
        if (attr == null) {
            return;
        }

        try {
            //默认是显示
            leftShow = attr.getBoolean(R.styleable.ShadowLayout_yc_leftShow, true);
            rightShow = attr.getBoolean(R.styleable.ShadowLayout_yc_rightShow, true);
            bottomShow = attr.getBoolean(R.styleable.ShadowLayout_yc_bottomShow, true);
            topShow = attr.getBoolean(R.styleable.ShadowLayout_yc_topShow, true);

            mCornerRadius = attr.getDimension(R.styleable.ShadowLayout_yc_cornerRadius, 0);
            mShadowLimit = attr.getDimension(R.styleable.ShadowLayout_yc_shadowLimit, 0);
            mDx = attr.getDimension(R.styleable.ShadowLayout_yc_dx, 0);
            mDy = attr.getDimension(R.styleable.ShadowLayout_yc_dy, 0);
            mShadowColor = attr.getColor(R.styleable.ShadowLayout_yc_shadowColor,
                    getResources().getColor(R.color.light_gray));
        } finally {
            attr.recycle();
        }
    }

    private TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] attr) {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0);
    }

    private Bitmap createShadowBitmap(int shadowWidth, int shadowHeight, float cornerRadius,
                                      float shadowRadius, float dx, float dy,
                                      int shadowColor, int fillColor) {

        //根据宽高创建bitmap背景
        Bitmap output = Bitmap.createBitmap(shadowWidth, shadowHeight, Bitmap.Config.ARGB_8888);
        //用画板canvas进行绘制
        Canvas canvas = new Canvas(output);
        RectF shadowRect = new RectF(shadowRadius, shadowRadius,
                shadowWidth - shadowRadius, shadowHeight - shadowRadius);

        if (dy > 0) {
            shadowRect.top += dy;
            shadowRect.bottom -= dy;
        } else if (dy < 0) {
            shadowRect.top += Math.abs(dy);
            shadowRect.bottom -= Math.abs(dy);
        }

        if (dx > 0) {
            shadowRect.left += dx;
            shadowRect.right -= dx;
        } else if (dx < 0) {
            shadowRect.left += Math.abs(dx);
            shadowRect.right -= Math.abs(dx);
        }

        Paint shadowPaint = new Paint();
        /*shadowPaint.setAntiAlias(true);
        shadowPaint.setColor(fillColor);
        shadowPaint.setStyle(Paint.Style.FILL);*/
        /*if (!isInEditMode()) {
            shadowPaint.setShadowLayer(shadowRadius, dx, dy, shadowColor);
        }*/
        shadowPaint.setMaskFilter(new BlurMaskFilter(30, BlurMaskFilter.Blur.SOLID));
        //shadowPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryOrange));
        //#eceff1
        shadowPaint.setColor(Color.parseColor("#e3f2fd"));
        setLayerType(View.LAYER_TYPE_SOFTWARE, shadowPaint);//取消硬件加速
        canvas.drawRoundRect(shadowRect, cornerRadius, cornerRadius, shadowPaint);
        return output;
    }
}
