package com.edgarxie.githubhands.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by edgar on 17-5-10.
 */

public class NewLineLayout extends ViewGroup {

    public NewLineLayout(Context context) {
        super(context,null);
    }

    public NewLineLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public NewLineLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        final int childLeft = getPaddingLeft();
        final int childTop = getPaddingTop();
        final int childRight = r -  l - getPaddingRight();
        int curLeft, curTop, maxHeight;
        maxHeight = 0;
        curLeft = childLeft;
        curTop = childTop;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE)
                continue;
            int curWidth, curHeight;
            curWidth = child.getMeasuredWidth();
            curHeight = child.getMeasuredHeight();
            if (curLeft + curWidth >= childRight) {
                curLeft = childLeft;
                curTop += maxHeight;
                maxHeight = 0;
            }
            child.layout(curLeft, curTop, curLeft + curWidth, curTop + curHeight);
            if (maxHeight < curHeight)
                maxHeight = curHeight;
            curLeft += curWidth;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxWidth=0;
        int maxHeight=0;
        int leftWidth=0;
        int topHeight=0;

        int widthSize=MeasureSpec.getSize(widthMeasureSpec);

        for (int i = 0; i < getChildCount(); i++) {
            View view=getChildAt(i);
            if (view.getVisibility() == GONE)
                continue;
            measureChild(view,widthMeasureSpec,heightMeasureSpec);

            maxWidth+=view.getMeasuredWidth();
            leftWidth+=view.getMeasuredWidth();

            if (leftWidth>widthSize){
                maxHeight+=topHeight;
                leftWidth=view.getMeasuredWidth();
                topHeight=view.getMeasuredHeight();
            }else {
                topHeight=Math.max(topHeight,view.getMeasuredHeight());
            }
        }
        maxHeight+=topHeight;
        maxWidth=Math.max(maxWidth,getSuggestedMinimumWidth());
        maxHeight=Math.max(maxHeight,getSuggestedMinimumHeight());

        setMeasuredDimension(resolveSizeAndState(maxWidth,widthMeasureSpec,0)
                ,resolveSizeAndState(maxHeight,heightMeasureSpec,0));
    }
}
