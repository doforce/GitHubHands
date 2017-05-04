package com.edgarxie.githubhands.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.edgarxie.githubhands.R;

/**
 * Created by edgar on 17-5-4.
 */

public class OvalTextView extends android.support.v7.widget.AppCompatTextView {

    public OvalTextView(Context context) {
        super(context,null);
    }

    public OvalTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public OvalTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackground(getResources().getDrawable(R.drawable.language_tab_bg,null));
        setPadding(10,10,10,10);
        setTextSize(getResources().getDimensionPixelSize(R.dimen.language_tab_text_size));
        setTextColor(getResources().getColor(R.color.language_tab_text_color,null));
    }
}
