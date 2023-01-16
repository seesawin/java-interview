package com.seesawin.javase;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.*;

public class JavaSE_02Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test2();
    }

    /**
     * Singleton 單例設計模式：
     * 1.某個類在整個系統只能有一個實例
     * - 構造器私有化
     * 2.必須是自行創建的，使用者不能隨意創建
     * - 使用靜態變量保存這個唯一的實例
     * 3.必須向整個系統提供這個實例
     * - 直接暴露靜態變量
     * - 用靜態變量的get獲取
     * <p>
     * <p>
     */
    public static void test2() throws ExecutionException, InterruptedException {
        /**
         * 惡漢式：
         * 1.線程安全
         * 2.再類初始化的時候直接創建實例
         */
        final var singleton1 = Singleton1.INSTANCE;
        final var singleton2 = Singleton2.INSTANCE;
        final var singleton3 = Singleton3.INSTANCE;
        System.out.println("info:" + singleton3.info);

        /**
         * 懶漢式：
         * 1.線程不安全
         * 2.延遲創建這個對象
         */
//        final var singleton4 = Singleton4.getInstance();
//        System.out.println(singleton4);
//        final var singleton41 = Singleton4.getInstance();
//        System.out.println(singleton41);

//        Callable<Singleton4> callable = () -> Singleton4.getInstance();
        Callable<Singleton4> callable = new Callable<Singleton4>() {
            @Override
            public Singleton4 call() throws Exception {
                return Singleton4.getInstance();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        final var f1 = executorService.submit(callable);
        final var f2 = executorService.submit(callable);
        final var r1 = f1.get();
        final var r2 = f2.get();
        System.out.println(r1 == r2);
        executorService.shutdown();

        /**
         * 懶漢式：
         * 2.線程安全版本
         */
        Callable<Singleton5> callable2 = () -> Singleton5.getInstance();

        ExecutorService executorService2 = Executors.newFixedThreadPool(5);
        final var f11 = executorService2.submit(callable2);
        final var f22 = executorService2.submit(callable2);
        final var r11 = f11.get();
        final var r22 = f22.get();
        System.out.println(r11 == r22);
        executorService2.shutdown();

        /**
         * 懶漢式：
         * 3.線程安全版本(優化使用內部類)
         */
        final var singleton6 = Singleton6.Inner.INSTANCE;
        final var singleton62 = Singleton6.Inner.INSTANCE;
        System.out.println(singleton6 == singleton62);
    }

    /**
     * 惡漢式 1.直接創建這個對象，不管你是否需要他
     */
    public static class Singleton1 {
        // 2.自行創建，使用靜態變量static保存
        // 3.使用public向外提供實例
        // 4.強調這個是一個單例，可以使用final修飾
        public static final Singleton1 INSTANCE = new Singleton1();

        // 1.構造器私有化
        private Singleton1() {
        }

    }

    /**
     * 惡漢式 2.枚舉類型，表示該類型的對象是有限的幾個，如果我們限定的單一就是單例模式
     */
    public enum Singleton2 {
        INSTANCE;
    }

    /**
     * 惡漢式 3.使用靜態區塊初始化數據
     */
    public static class Singleton3 {
        public static final Singleton3 INSTANCE;
        private String info;

        static {
            final Properties prop = new Properties();
            try {
                prop.load(Singleton3.class.getClassLoader().getResourceAsStream("singleton.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            INSTANCE = new Singleton3(prop.getProperty("info"));
        }

        private Singleton3(final String info) {
            this.info = info;
        }
    }

    public static class Singleton4 {
        // 2.自行創建，使用靜態變量static保存
        // 3.使用private，不直接向外提供實例
        public static Singleton4 instance;

        // 1.構造器私有化
        private Singleton4() {
        }

        public static Singleton4 getInstance() throws InterruptedException {
            if (Objects.isNull(instance)) {
                TimeUnit.MILLISECONDS.sleep(1000);
                instance = new Singleton4();
            }
            return instance;
        }

    }

    /**
     * 線程安全
     */
    public static class Singleton5 {
        public static Singleton5 instance;

        private Singleton5() {
        }

        public static Singleton5 getInstance() throws InterruptedException {
            if (Objects.isNull(instance)) {
                synchronized (Singleton5.class) {
                    if (Objects.isNull(instance)) {
                        TimeUnit.MILLISECONDS.sleep(1000);
                        instance = new Singleton5();
                    }
                }
            }
            return instance;
        }

    }

    /**
     * 線程安全(優化)
     * 1.在內部類別被初始化時才創建INSTANCE
     * 2.靜態內部類不會隨著外部類被加載和初始化而初始化
     * 3.因為是在內部類加載和初始化時所創建的，因此是線程安全的
     */
    public static class Singleton6 {

        private Singleton6() {
        }

        private static class Inner {
            private static final Singleton6 INSTANCE = new Singleton6();
        }

        public static Singleton6 getInstance() throws InterruptedException {
            return Inner.INSTANCE;
        }

    }

}
