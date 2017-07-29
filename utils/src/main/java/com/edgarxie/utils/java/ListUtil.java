package com.edgarxie.utils.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgar on 17-2-21.
 */

public class ListUtil {

    public static String listToString(List<String> list,String separate){
        StringBuilder builder=new StringBuilder();
        if (list!=null && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i));
                if (i!=list.size()-1){
                    builder.append(separate);
                }
            }
            return builder.toString();
        }
        return "";
    }

    public static String listToString(List<String> list){
       return listToString(list,",");
    }

    public static List<String> stringToList(String characters,String separate){
        List<String> result = new ArrayList<>();
        if (characters!=null && !characters.equals(""));{
            String[] temp = characters.split(separate);
            for (int i = 0; i < temp.length; i++) {
                result.add(temp[i]);
            }
        }
        return result;
    }

    public static List<String> stringToList(String characters){
        return stringToList(characters,",");
    }
}
