package com.edgarxie.utils.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.edgarxie.utils.java.CloseUtil;
import com.edgarxie.utils.functional.BiConsumers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by edgarx on 16-9-4.
 */
public class BitmapUtil {

    public static Bitmap getSmallBitmap(String srcPath,int targetKb) {
        return getSmallBitmap(srcPath,targetKb,(width,height) -> {
            width=480f;
            height=800f;
        });
    }

    public static Bitmap getSmallBitmap(String srcPath, int targetKb
            , BiConsumers<Float,Float> widthHeight) {
        Bitmap bitmap=BitmapFactory.decodeFile(srcPath,scaleBitmap(srcPath,widthHeight
                ,((options, s) -> BitmapFactory.decodeFile(s,options))));
        return compressImage(bitmap,targetKb);
    }

    public static Bitmap getSmallBitmap(Bitmap image,int targetKb) {
        return getSmallBitmap(image,targetKb,(width,height) ->{
            width=480f;
            height=800f;
        });
    }

    public static Bitmap getSmallBitmap(Bitmap image,int targetKb,BiConsumers<Float,Float> widthHeight) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if( baos.toByteArray().length / 1024>1024) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        return compressImage(BitmapFactory.decodeStream(isBm, null, scaleBitmap(isBm,widthHeight
                ,(options, inputStream) -> BitmapFactory.decodeStream(isBm,null,options))),targetKb);
    }


    public static Bitmap compressImage(Bitmap image,int targetKb) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        while ( baos.toByteArray().length / 1024>targetKb) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }

    public static SpannableString appendImages(Context context,Bitmap bitmap,String url,int index,Editable edit_text){
        ImageSpan imageSpan = new ImageSpan(context, bitmap);
        String tempUrl = "<img src=\"" + url + "\" />";
        SpannableString spannableString = new SpannableString(tempUrl);
        spannableString.setSpan(imageSpan, 0, tempUrl.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (index < 0 || index >= edit_text.length()) {
            edit_text.append(spannableString);
        } else {
            edit_text.insert(index, spannableString);
        }
        return spannableString;
    }

    public static Bitmap getBitmapFromUri(Context context,Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media
                    .getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static void saveBitmap2Sdcard(Bitmap bitmap, int quality,String path){
        FileOutputStream out=null;
        File file=null;
        try {
            file=new File(path);
            file.createNewFile();
            out=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality,out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            CloseUtil.close(out);
        }
    }

    public static Bitmap getBitmap(String path){
        Bitmap bitmap=null;
        bitmap= BitmapFactory.decodeFile(path);
        return bitmap;
    }

    private static <T> BitmapFactory.Options scaleBitmap(T source
            ,BiConsumers<Float,Float> widthHeight,BiConsumers<BitmapFactory.Options,T> loadOption){
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        loadOption.accept(newOpts,source);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float ww = 480f;
        float hh = 800f;
        widthHeight.accept(ww,hh);
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        return newOpts;
    }


}
