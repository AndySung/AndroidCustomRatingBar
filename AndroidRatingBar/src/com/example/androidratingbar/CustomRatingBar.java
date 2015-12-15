package com.example.androidratingbar;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.RatingBar;
/**
 * 自定义音量调节显示控件
 * @author miaowei
 *
 */
public class CustomRatingBar extends RatingBar{
	
	public CustomRatingBar(Context context) {
		super(context);
	}

	public CustomRatingBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 重写onSizeChanged，因为从水平变成垂直，所以要交换Width和Height
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(h, w, oldh, oldw);
	}
	/**
	 * 重写onDraw，关键旋转，这里可以自定义在原来水平的基础上旋转角度
	 */
	@Override
	protected synchronized void onDraw(Canvas canvas) {
		// 旋转
		canvas.rotate(-90);
        //控制左右位置，要自己调试
		canvas.translate(-this.getHeight(),0);
		super.onDraw(canvas);
	}
	
	@Override
    protected synchronized void onMeasure(int widthMeasureSpec,
            int heightMeasureSpec)
    {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

}
