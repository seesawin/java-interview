package com.seesawin.javase;

public class JavaSE_01Test {

    public static void main(String[] args) {
        test1();
    }

    /**
     * 自增變量
     * <p>
     * 觀念
     * 1.局部變量表
     * 2.操作數棧
     * <p>
     * 總結
     * 1.賦值=，最後計算
     * 2.=右邊從左到右順序押入操作數棧
     * 3.先乘除後加減
     * *4.自增自減是直接改變局部變量表，不經過操作數棧
     * 5. 最後賦值之前的臨時結果是存在操作數棧當中
     */
    public static void test1() {
        int i = 1;
        // 1.將i放入操作數棧，i = 1
        // 2.i自增1，局部變量表的i為2
        // 3.賦值i，將操作數棧的i(值為1)賦值給局部變量表的i(由2變成1)
        i = i++;
        System.out.println("i: " + i);
        System.out.println("-----");
        // 1.將i放入操作數棧，i = 1
        // 2.i自增1，局部變量表的i為2
        // 3.賦值j，將操作數棧的i(值為1)賦值給局部變量表的j(值為1)
        int j = i++;
        System.out.println("i: " + i + ", j: " + j);
        System.out.println();
        System.out.println("-----");
        // 1.將i放入操作數棧，i = 2
        // 2.++i: i自增1，局部變量表的i為3，將i放入操作數棧，i = 3
        // 3.i++: 將i放入操作數棧，i = 3，i自增1，局部變量表的i為4
        // 4.k = 2 + 3 * 3
//        int k = i + ++i * i++;
//        System.out.println("i: " + i + ", j: " + j + ", k: " + k);

        // 1.將i放入操作數棧，i = 2
        // 2.i++: 將i放入操作數棧，i = 2，i自增1，局部變量表的i為3
        // 3.++i: i自增1，局部變量表的i為4，將i放入操作數棧，i = 4
        // 4.k = 2 + 2 * 4
        int k = i + i++ * ++i;
        System.out.println("i: " + i + ", j: " + j + ", k: " + k);
    }

}
