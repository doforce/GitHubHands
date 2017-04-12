package com.edgarxie.utils.java;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by edgar on 17-2-21.
 */

public class ListUtil<T> {

    public List<T> removeRepeat(List<T> list){
        Set<T> set=new LinkedHashSet<>(list);
        list.clear();
        list.addAll(set);
        return list;
    }
}
