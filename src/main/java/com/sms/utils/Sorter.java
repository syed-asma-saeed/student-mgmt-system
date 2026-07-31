package com.sms.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorter<T>{
    public List<T> sort(List<T> list, Comparator<T> comparator){
        List<T> temp = new ArrayList<>(list);

        Collections.sort(temp, comparator);

        return temp;
    }
}