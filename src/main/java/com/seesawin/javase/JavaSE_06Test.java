package com.seesawin.javase;

/**
 * 1.就近原則
 * <p>
 * 2.變量的分類
 * - 成員變量：類變量、實例變量
 * - 局部變量
 * <p>
 * 3.非靜態代碼塊執行：每次創建對象都會執行
 * <p>
 * 4.方法的調用規則：調用一次執行一次
 */
public class JavaSE_06Test {
    static int s;
    int i;
    int j;

    {
        int i = 1;
        i++;
        j++;
        s++;
    }

    public void test(int j) {
        j++;
        i++;
        s++;
    }

    /**
     * 局部變量與成員變量的區別
     * # 聲明的位置
     * 1.局部變量
     * - 型參：args
     * - 方法體：ex1, ex2...
     * - {}：代碼塊
     * <p>
     * 2.成員變量
     * - 類中方法外：s,i,j
     * a.類變量：有static
     * b.實例變量：沒有static
     * <p>
     * # 修飾符
     * 1.局部變量：final
     * 2.成員變量：public, protected, private, final, static, volatile, transient
     * <p>
     * #值儲存的位置
     * 1.局部變量：棧(stack)
     * - 是指虛擬機棧，用藍儲存局部變量表等。局部變量表存放了編譯期可知長度的各種
     * a.基本數據類型(boolean, byte, char, short, int, float, long, double)
     * b.對象引用(reference類型，它不同於對象本身，是對象在堆內存的首地址)。方法執行完自動釋放
     * <p>
     * 2.實例變量：堆(heap)
     * - 此內存區域唯一的目的就是存放對象實例，幾乎所有的對象實例都在這分配內存。這一點在java虛擬機的描述是：所有的對象實例以及
     * 數組都要在堆上分配
     * <p>
     * 3.類變量：方法區(method area)
     * - 用於儲存已被虛擬機加載的類訊息、常量、靜態變量、即時編譯器編譯後的代碼等數據
     * <p>
     * 4.作用域
     * - 局部變量：從聲明處開始，到所屬的"}"結束
     * - 實例變量：在當前“this.”(有時this.可省略)，在其他類中以“對象名.”訪問
     * - 類變量：在當前類中“類名.”(有時類名.可省略)，在其他類中以“類名.”或是“對象名.”訪問
     * <p>
     * 5.生命週期
     * - 局部變量：每一個線程、每一次調用都是新的生命週期
     * - 實例變量：隨著對象的創建而初始化，隨著對象被回收而消亡，每一個對象的實例變量是獨立的
     * - 類變量：隨著類的創建而初始化，隨著類的卸載而消亡，該類的所有對象的類變量是共享的
     * <p>
     * 6.局部變量重名
     * - 實例變量：實例變量前加this
     * - 類變量：類變量前加this or 類名.
     *
     * @param args
     */
    public static void main(String[] args) {
        // i=0, j=1, s=1
        final var ex1 = new JavaSE_06Test();
        // i=0, j=1, s=2
        final var ex2 = new JavaSE_06Test();
        // i=1, j=1, s=3
        ex1.test(10);
        // i=2, j=1, s=4
        ex1.test(20);
        // i=1, j=1, s=5
        ex2.test(30);
        System.out.println("i: " + ex1.i + ", j: " + ex1.j + ", s: " + ex1.s);
        System.out.println("i: " + ex2.i + ", j: " + ex2.j + ", s: " + ex2.s);
    }
}
