package com.vindroid.myapp;

import java.util.HashMap;
import java.util.Map;

public class TempClass {

    public static void main(String arg[]) {
        TempClass tempClass = new TempClass();
        int startTime = 1;
        int endTime = 3;

        System.out.println("slot_available ="+tempClass.method(startTime, endTime));
    }

    private boolean method(int startTime, int endTime) {
        boolean isAvailable = false;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(4, 4);
        map.put(9, 9);
        map.put(10, 13);
        map.put(15, 15);


        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() <= startTime && entry.getValue() >= startTime) {
                isAvailable= true;
                break;
            }else if (entry.getKey() <= endTime && entry.getValue() >= endTime ){
                isAvailable=true;
                break;
            }else if(startTime<=entry.getKey() && endTime>=entry.getValue()){
                isAvailable =true;
                break;

            }
        }
        return isAvailable;
    }
}
