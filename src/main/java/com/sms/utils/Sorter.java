package com.sms.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorter<T>{
    public List<T> sort(List<T> list, Comparator<T> comparator){
        List<T> temp = new ArrayList<>(list);

        //Collections.sort(temp) only works when the elements of temp implement Comparable, because Java needs the objects themselves to know how to compare each other. Since your generic type T has no such guarantee, you should use the Comparator<T> parameter you provided, which tells Java how to compare and sort the objects
        //Collections.sort(temp, comparator);
        temp.sort(comparator); //will also work

        return temp;
    }
}