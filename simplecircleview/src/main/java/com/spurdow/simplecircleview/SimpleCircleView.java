package com.spurdow.simplecircleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by davidluvellejoseph on 7/20/16.
 */
public class SimpleCircleView extends View {
    static final int DEFAULT_RADIUS = 25;
    static final int DEFAULT_FILL_COLOR = Color.parseColor("#FF451AD2");
    static final int DEFAULT_STROKE_COLOR = Color.parseColor("#FF041889");
    /**
     * Paint
     */
    private Paint circlePaint;
    private Paint circleStrokePaint;

    /**
     * Attribute from attrs.xml
     */
    private int radius;
    private int fillColor;
    private int strokeColor;


    /**
     * var that handles dynamic changes to our view
     */
    private float diameter;
    private float w,h,wp,hp;

    public SimpleCircleView(Context context) {
        super(context);
        init(null);
    }

    public SimpleCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SimpleCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.FILL);
        circleStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circleStrokePaint.setStyle(Paint.Style.STROKE);
        circleStrokePaint.setStrokeWidth(2);

        if(attrs != null) {
            TypedArray tArray = getContext().obtainStyledAttributes(attrs, R.styleable.circleviewStyles);
            radius = tArray.getInteger(R.styleable.circleviewStyles_radius, DEFAULT_RADIUS);
            fillColor = tArray.getColor(R.styleable.circleviewStyles_fillColor, DEFAULT_FILL_COLOR);
            strokeColor = tArray.getColor(R.styleable.circleviewStyles_strokeColor, DEFAULT_STROKE_COLOR);
            tArray.recycle();
        }else{
            radius = DEFAULT_RADIUS;
            fillColor = DEFAULT_FILL_COLOR;
            strokeColor = DEFAULT_STROKE_COLOR;
        }

        circlePaint.setColor(fillColor);
        circleStrokePaint.setColor(strokeColor);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
//    {
//
//        int measuredWidth = measureWidth(widthMeasureSpec);
//        if(radius == 0) // No radius specified.
//        {                     // Lets see what we can make.
//            // Check width size. Make radius half of available.
//            radius = measuredWidth / 2;
//            int tempRadiusHeight = measureHeight(heightMeasureSpec) / 2;
//            if(tempRadiusHeight < radius)
//                // Check height, if height is smaller than
//                // width, then go half height as radius.
//                radius = tempRadiusHeight;
//        }
////        // Remove 2 pixels for the stroke.
////        int circleDiameter = circleRadius * 2 - 2;
////        // RectF(float left, float top, float right, float bottom)
////        circleArc = new RectF(0, 0, circleDiameter, circleDiameter);
//        int measuredHeight = measureHeight(heightMeasureSpec);
//        setMeasuredDimension(measuredWidth, measuredHeight);
//        Log.d("onMeasure() ::", "measuredHeight =>" + String.valueOf(measuredHeight) + "px measuredWidth => " + String.valueOf(measuredWidth) + "px");
//    }
//
//    private int measureHeight(int measureSpec) {
//        int specMode = MeasureSpec.getMode(measureSpec);
//        int specSize = MeasureSpec.getSize(measureSpec);
//        int result = 0;
//        if (specMode == MeasureSpec.AT_MOST) {
//            result = radius * 2;
//        } else if (specMode == MeasureSpec.EXACTLY) {
//            result = specSize;
//        }
//        return result;
//    }
//
//    private int measureWidth(int measureSpec) {
//        int specMode = MeasureSpec.getMode(measureSpec);
//        int specSize = MeasureSpec.getSize(measureSpec);
//        int result = 0;
//        if (specMode == MeasureSpec.AT_MOST) {
//            result = specSize;
//        } else if (specMode == MeasureSpec.EXACTLY) {
//            result = specSize;
//        }
//        return result;
//    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Account for padding
        float xpad = (float)(getPaddingLeft() + getPaddingRight());
        float ypad = (float)(getPaddingTop() + getPaddingBottom());

        this.w = (float)w - xpad;
        this.h = (float)h - ypad;
        this.wp = w;
        this.hp = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(w / 2 , h / 2 , radius , circlePaint);
        canvas.drawCircle(wp / 2 , hp / 2 , radius , circleStrokePaint);
    }


    /**
     * Setter getter for our attributes
     */
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
        invalidate();
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        invalidate();
    }
}
