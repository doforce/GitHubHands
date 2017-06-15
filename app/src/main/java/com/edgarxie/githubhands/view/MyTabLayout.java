package com.edgarxie.githubhands.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by edgar on 17-5-1.
 */

public class MyTabLayout extends TabLayout {
    public MyTabLayout(Context context) {
        super(context);
    }

    public MyTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTabClickListener(View.OnClickListener listener){
        for (int i = 0; i < getTabCount(); i++) {
            TabLayout.Tab tab = getTabAt(i);
            if (tab == null) return;
            Class c = tab.getClass();
            try {
                Field field = c.getDeclaredField("mView");
                field.setAccessible(true);
                final View view = (View) field.get(tab);
                if (view == null) return;
                view.setTag(i);
                view.setOnClickListener(listener);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
