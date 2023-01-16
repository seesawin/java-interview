package com.seesawin.javase;

public class JavaSE_03Test {

    /**
     * 考點
     * 1.類初始化過程
     * a.一個類要創建實例需要先加載並初始化該類
     * - main方法所在該類需要先加載和初始化
     * b.一個子類要初始化需要先初始化父類
     * c.一個類初始化就是執行<clinit>方法
     * - <clinit>
     * - 由靜態變量顯示賦值代碼和靜態代碼塊組成
     * - 方法由上到下順序執行
     * - 方法只執行一次
     * <p>
     * 2.實例初始化過程
     * a. 就是執行<init>方法
     * -<init>
     * - 可能重載有多個，有幾個構造器就有幾個<init></>方法
     * - 由非靜態實例變量顯示賦值代碼和非靜態模塊組成，對應構造器代碼組成
     * - 非靜態實例變量顯示賦值代碼和非靜態模塊從上到下順序執行，而對應的構造器代碼最後執行
     * - 每次創建實例對象，調用對應的構造器，執行的就是對應的<init>方法
     * - 方法的首行是super()或是super(實參列表)，即對應父類的<init>方法
     * <p>
     * 3.方法重寫
     * a.哪些方法不可以被重寫
     * - final方法
     * - static方法
     * - private子類不可見方法
     * b.對象的多態性
     * - 子類如果重寫父類方法，通過子類對象調用一定是子類重寫過的代碼
     * - 非靜態方法默認的對象默認是this
     * - this對象在構造氣或者說<this>方法中就是正在創建的對象
     * <p>
     * 進階：
     * Override和Overload的區別？
     * - 覆寫(Override)是指"子類別"可以覆寫父類別的方法內容，使該方法擁有不同於父類別的行為。
     * - 多載(Overload)指在"一個類別(class)中"，定義多個名稱相同，但參數(Parameter)不同的方法(Method)。
     * - 多型(Polymorphism)是指父類別可透過子類別衍伸成多種型態，而父類別為子類別的通用型態，
     * 再透過子類別可覆寫父類別的方法來達到多型的效果，也就是"同樣的方法名稱會有多種行為"。
     * <p>
     * Override重寫的要求？
     * - 方法名
     * - 型參列表
     * - 返回值類型
     * - 拋出的異常列表
     * - 修飾符
     *
     * @param args
     */
    public static void main(String[] args) {
        final var son = new Son();
        System.out.println("-----");
        final var son2 = new Son();
        System.out.println("-----");
        final var father = new Father();
    }

    /**
     * 父類初始化<clinit>
     * 步驟：
     * 1.j = method()
     * 2.父類靜態代碼塊
     * <p>
     * 父類的實例化方法
     * 1.super()
     * 2.i = test()
     * 3.父類非靜態代碼塊
     * 4.父類無參構造
     * <p>
     * 非靜態方法前面其實有一個默認的this
     * this在構造器(或<init>)他表示的是正在創建的對象，因為這裡是在創建Son對象，因此this代表的是Son，
     * 所以test是在執行子類重寫的代碼(面向對象的多態)
     */
    public static class Father {
        private int i = test();
        private static int j = method();

        static {
            System.out.println("(1)");
        }

        Father() {
            System.out.println("(2)");
        }

        {
            System.out.println("(3)");
        }

        public int test() {
            System.out.println("(4)");
            return 1;
        }

        public static int method() {
            System.out.println("(5)");
            return 1;
        }
    }

    /**
     * 先初始化父類 (5)(1)
     * 初始化子類 (10)(6)
     * <p>
     * 子類初始化<clinit>
     * 1.j = method()
     * 2.子類靜態代碼塊
     * <p>
     * 子類的實例化方法
     * 1.super() = 父類的實例化方法 (9) (3) (2)
     * 2.i = test() (9)
     * 3.子類非靜態代碼塊 (8)
     * 4.子類無參構造 (7)
     */
    public static class Son extends Father {
        private int i = test();
        private static int j = method();

        static {
            System.out.println("(6)");
        }

        Son() {
            // 寫或不寫都在，一定會調用父類的構造器
//            super();
            System.out.println("(7)");
        }

        {
            System.out.println("(8)");
        }

        public int test() {
            System.out.println("(9)");
            return 1;
        }

        public static int method() {
            System.out.println("(10)");
            return 1;
        }

    }

}
