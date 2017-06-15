package com.edgarxie.utils.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by X on 2016/8/7 0007.
 */
public class Validation {

    public static boolean isEmailValid(String email){
        boolean flag;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    public static boolean isPwdValid(String pwd){
        return Pattern.compile("^\\d+|\\S{0,5}$").matcher(pwd).matches();
    }
}
