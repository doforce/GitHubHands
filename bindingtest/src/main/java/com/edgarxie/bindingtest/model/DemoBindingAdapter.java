package com.edgarxie.bindingtest.model;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by edgar on 17-4-6.
 */

public class DemoBindingAdapter  {

    @BindingAdapter({"app:imageUrl","app:placeHolder"})
    public static void loadImage(ImageView imageView, String url, Drawable drawable){
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(drawable)
                .into(imageView);
    }
}
