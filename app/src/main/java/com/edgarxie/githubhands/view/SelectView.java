package com.edgarxie.githubhands.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by edgar on 17-5-4.
 */

public class SelectView extends ViewGroup {
//    private ArrayList<String> mValues;
//    private ArrayList<TextView> mChildrenViews=new ArrayList<>();

    public SelectView(Context context) {
        super(context,null);
    }

    public SelectView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public SelectView(Context context, AttributeSet attrs, int defStyleAttr) {
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

//    private void addTextView(String text,int index){
//        TextView textView= (TextView) LayoutInflater.from(mContext).inflate(R.layout.oval_text_view,this);
//        textView.setText(text);
//        mChildrenViews.add(index,textView);
//        addView(textView,index);
//    }
//
//    public void setValues(ArrayList<String> mValues) {
//        this.mValues = mValues;
//        for (int i = 0; i < mValues.size(); i++) {
//            addTextView(mValues.get(i),i);
//        }
//    }
//
//    public ArrayList<String> getValues() {
//        return mValues;
//    }
//
//    public void getValue(int index){
//        if (mValues.size()>0) {
//            mValues.get(index);
//        }
//    }
//
//    public void addVaule(int index, String value){
//        mValues.add(index,value);
//        addTextView(value,index);
//    }
//
//    public void addVaule(String value){
//        mValues.add(value);
//        addTextView(value,-1);
//    }
//
//    public void removeValue(int index){
//        if (mValues.size()>0) {
//            mValues.remove(index);
//            removeView(mChildrenViews.get(index));
//        }
//    }
}
