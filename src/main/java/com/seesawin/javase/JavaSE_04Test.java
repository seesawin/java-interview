package com.seesawin.javase;

import java.util.Arrays;
import java.util.stream.Collectors;

public class JavaSE_04Test {
    /**
     * 考點
     * - 方法參數的傳遞性
     * - String, 包裝類等對象的不可變性
     * <p>
     *
     * @param args
     */
    public static void main(String[] args) {
        // 局部變量按照方法分區(main方法棧)
        // 常數
        int i = 1;
        // 存在常量池，String不可變
        String str = "hello";
        // 存在堆，包裝類不可變
        Integer num = 200;
        // 存在堆
        int[] arr = {1, 2, 3, 4, 5};
        // 存在堆
        final var data = new MyData();

        // 實參列表
        // 實參給型參賦值
        // - 基本數據類型：傳遞數值
        // - 引用數據類型：傳遞地址值
        change(i, str, num, arr, data);

        System.out.println("i: " + i);
        System.out.println("str: " + str);
        System.out.println("num: " + num);
        System.out.println("arr: " + Arrays.toString(arr));
        System.out.println("data.a: " + data.a);

    }

    // 局部變量(change方法棧)
    // 型參列表
    public static void change(int i, String str, Integer num, int[] arr, MyData data) {
        // 常數
        i += 1;
        // 存在常量池，String不可變，會產生新的字串hello world
        // str會被賦予新的地址值
        str += " world";
        // 存在堆，包裝類不可變，會產生新的Integer300
        // num會被賦予新的地址值
        num += 100;
        // 存在堆
        arr[0] += 1;
        // 存在堆
        data.a += 1;
    }

    public static class MyData {
        int a = 10;
    }
}
