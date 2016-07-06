package com.dreamer.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysx on 2016/7/5.
 */
public class RangeBar extends View {

    private Paint mHorizontalPaint;
    private Paint mVerticalPaint;
    private Paint mCirclePaint;
    /** 分段个数 */
    private int mRangeNum;
    private int mVerticalLineWidth;
    private int mVerticalLineHeight;
    private int mHorizontalLineHeight;
    private int mThumbRadius;
    /** 当前选中的位置 */
    private int mSelectPosition = -1;

    private List<RectF> mRects;

    private OnRangeBarListener mListener;

    public RangeBar(Context context) {
        this(context, null);
    }

    public RangeBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RangeBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RangeBar);
        int background = ta.getColor(R.styleable.RangeBar_range_background, Color.parseColor("#8a8a8a"));
        int thumbBg = ta.getColor(R.styleable.RangeBar_thumb_color, Color.parseColor("#33475f"));

        mThumbRadius = ta.getDimensionPixelSize(R.styleable.RangeBar_thumb_radius,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
                        getResources().getDisplayMetrics()));

        mHorizontalLineHeight = ta.getDimensionPixelSize(R.styleable.RangeBar_horizontal_line_height,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
                        getResources().getDisplayMetrics()));

        mVerticalLineWidth = ta.getDimensionPixelSize(R.styleable.RangeBar_vertical_line_width,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
                        getResources().getDisplayMetrics()));

        mVerticalLineHeight =  ta.getDimensionPixelSize(R.styleable.RangeBar_vertical_line_height,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
                        getResources().getDisplayMetrics()));

        mRangeNum = ta.getInt(R.styleable.RangeBar_range_num, 5);
        ta.recycle();

        mHorizontalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHorizontalPaint.setStrokeWidth(mHorizontalLineHeight);
        mHorizontalPaint.setColor(background);
        mHorizontalPaint.setStrokeCap(Paint.Cap.SQUARE);

        mVerticalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mVerticalPaint.setStrokeWidth(mVerticalLineWidth);
        mVerticalPaint.setColor(background);
        mVerticalPaint.setStrokeCap(Paint.Cap.SQUARE);

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(thumbBg);
        mRects = new ArrayList<>();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        int width = getWidth() - getPaddingRight() - getPaddingLeft();
        // Log.d("RangeBar", "width:" + width);
        int height = getHeight() - getPaddingTop() - getPaddingBottom();
        int startY = height / 2 - mVerticalLineHeight;
        int endY = height / 2 + mVerticalLineHeight;
        // Log.d("RangeBar", "startY:" + startY +", endY=" + endY);
        // 画底线
        canvas.drawLine(getPaddingLeft(), height / 2,
                getWidth() - getPaddingRight(), height / 2, mHorizontalPaint);

        // 画分段线
        float translationX = (float)width / (mRangeNum - 1);
        for (int i = 0; i < mRangeNum; i++) {
            canvas.drawLine(getPaddingLeft(), startY, getPaddingLeft(), endY, mVerticalPaint);
            // 平移画布
            canvas.translate(translationX, 0);
        }

        mRects.clear();
        for (int i = 0; i < mRangeNum; i++) {
            RectF rf;
            if (i == 0) {
                rf = new RectF(getPaddingLeft(),
                        getTop(),
                        translationX / 2 + getPaddingLeft(),
                        getBottom());
            } else if (i == mRangeNum - 1) {
                rf = new RectF(width - translationX / 2 + 10,
                        getTop(),
                        width + getPaddingRight(),
                        getBottom());

            } else {
                rf = new RectF((i - 1) * translationX + translationX / 2 + 10,
                        getTop(),
                        i * translationX + translationX / 2 + getPaddingLeft(),
                        getBottom());
            }
            mRects.add(rf);
        }
        if (mSelectPosition >= 0) {
            canvas.restore();
            canvas.drawCircle(getPaddingLeft() + mSelectPosition * translationX,
                    height / 2, mThumbRadius, mCirclePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < mRangeNum; i++) {
                    RectF rectF = mRects.get(i);
                    boolean contains = rectF.contains(x, y);
                    if (contains && mSelectPosition != i) {
                        mSelectPosition = i;
                        postInvalidate();
                        // Log.d("RangeBar", "点击位置 " + i +",pos="+mSelectPosition);
                        if (mListener != null) {
                            // position从0开始
                            mListener.onClick(i);
                        }
                    }

                }
                break;
        }
        return true;
    }

    /**
     * 改变Range的个数
     * @param num
     */
    public void changeRangeNum(int num) {
        if (mRangeNum >= 2) {
            mRangeNum += num;
            postInvalidate();
        }
    }

    public void setOnRangeBarListener(OnRangeBarListener listener) {
        mListener = listener;
    }

    public interface OnRangeBarListener {
        void onClick(int position);
    }
}
