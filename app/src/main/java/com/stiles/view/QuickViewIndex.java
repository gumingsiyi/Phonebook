package com.stiles.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.stiles.phonebook.R;

/**
 * Created by stiles on 16/5/8.
 */
public class QuickViewIndex extends View {
    private int height;
    private int width;
    private int touchedIndex = -1;
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;

    private TextView mTextDialog;

    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }

    private String[] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };
    private Paint paint = new Paint();

    public QuickViewIndex(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(16);
        paint.setAntiAlias(true);
    }

    public QuickViewIndex(Context context, AttributeSet attributeSet, int defaultStyle) {
        super(context, attributeSet, defaultStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height = getHeight();
        width = getWidth();

        int singleHeight = height / indexArr.length - 2;

        for (int i = 0; i < indexArr.length; i++) {
            paint.setColor(Color.rgb(33, 65, 98));  //设置字体颜色
            paint.setTypeface(Typeface.DEFAULT_BOLD);  //设置字体
            paint.setAntiAlias(true);  //设置抗锯齿
            paint.setTextSize(30);  //设置字母字体大小
            // 选中的状态
            if (i == touchedIndex) {
                paint.setColor(Color.parseColor("#3399ff"));  //选中的字母改变颜色
                paint.setFakeBoldText(true);  //设置字体为粗体
            }
            // x坐标等于中间-字符串宽度的一半.
            float xPos = width / 2 - paint.measureText(indexArr[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(indexArr[i], xPos, yPos, paint);  //绘制所有的字母
            paint.reset();// 重置画笔
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();// 点击y坐标
        final int oldChoose = touchedIndex;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        final int c = (int) (y / getHeight() * indexArr.length);// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.

        switch (action) {
            case MotionEvent.ACTION_UP:
                setBackgroundResource(R.color.colorWhite);
                touchedIndex = -1;

                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }

                invalidate();
                break;

            default:
                setBackgroundResource(R.color.colorPrimary);
                if (oldChoose != c) {  //判断选中字母是否发生改变
                    if (c >= 0 && c < indexArr.length) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(indexArr[c]);
                        }

                        if (mTextDialog != null) {
                            mTextDialog.setText(indexArr[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }

                        touchedIndex = c;
                        invalidate();
                    }
                }

                break;
        }
        return true;
    }
    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String s);
    }
}
