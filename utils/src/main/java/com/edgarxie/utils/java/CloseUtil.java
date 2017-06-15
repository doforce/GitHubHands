package com.edgarxie.utils.java;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by edgarx on 16-8-3.
 */
public class CloseUtil {
    private CloseUtil(){};

    public static void close(Closeable closeable){
        if (null!=closeable){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
