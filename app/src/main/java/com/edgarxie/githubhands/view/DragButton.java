package com.edgarxie.githubhands.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Window;

import com.edgarxie.githubhands.R;

/**
 * Created by edgar on 17-5-15.
 */

public class DragButton extends android.support.v7.widget.AppCompatButton {
    private boolean isDrag=true;
    private OnButtonClicked onButtonClicked;
    private OnButtonLongClicked onButtonLongClicked;
    private Window window;
    private int lastX;
    private int lastY;

    public DragButton(Context context) {
        super(context,null);
    }

    public DragButton(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public DragButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setTextColor(getResources().getColor(R.color.language_tab_text_color));
        setBackground(getResources().getDrawable(R.drawable.language_tab_bg));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int offX,offY;
        int x= (int) event.getX();
        int y= (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX =  x;
                lastY=y;
                if (!isDrag) {
                    onButtonClicked.onClicked(getText().toString());
                }
                break;
            case MotionEvent.ACTION_MOVE:
                offX=x-lastX;
                offY=y-lastY;
                offsetLeftAndRight(offX);
                offsetTopAndBottom(offY);
                break;
            case MotionEvent.ACTION_UP:
            break;
        }
        return true;
    }

    public int getStatusBarHeight(){
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    public int getTitleBarHeight(){
        int contentViewTop = window
                .findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight = contentViewTop - getStatusBarHeight();
        return titleBarHeight;
    }

    public interface OnButtonClicked{
        void onClicked(String text);
    }

    public interface OnButtonLongClicked{
        void onLongClicked(String text);
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public void setOnButtonClicked(OnButtonClicked onButtonClicked) {
        this.onButtonClicked = onButtonClicked;
    }

    public void setOnButtonLongClicked(OnButtonLongClicked onButtonLongClicked) {
        this.onButtonLongClicked = onButtonLongClicked;
    }
}
