package com.edgarxie.utils.java;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by edgarx on 16-9-4.
 */
public class FileUtil {

    public  static String getPathFromURI(Context context,Uri contentURI) {
        String result;
        Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public static File createFolder(String folderName){
        File photofile = new File(folderName);
        if (!photofile.exists()) {
            photofile.mkdirs();
        }
        return photofile;
    }

    public static String getSdcardPath(){
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static void delete(String path) {
        File file=new File(path);
        if (file.exists() && file.isFile()) {
            file.delete();
            return;
        }
        if(file.exists() && file.isDirectory()){
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }
            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i].getAbsolutePath());
            }
            file.delete();
        }
    }
}
