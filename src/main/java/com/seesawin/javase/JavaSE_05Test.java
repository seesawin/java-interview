package com.seesawin.javase;

public class JavaSE_05Test {
    public static void main(String[] args) {
        final var start = System.currentTimeMillis();
//        System.out.println(test1(50));
        System.out.println(test2(50));
        System.out.println(System.currentTimeMillis() - start);

    }

    public static int test1(final int i) {
        if (i < 1) {
            throw new RuntimeException("must gt 1");
        }
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        return test1(i - 2) + test1(i - 1);
    }

    public static int test2(final int i) {
        if (i < 1) {
            throw new RuntimeException("must gt 1");
        }
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        // 初始化走到第二級台階的走法
        int one = 2;
        // 初始化走到第一級台階的走法
        int two = 1;
        int sum = 0;

        for (int j = 3; j < i; j++) {
            sum = two + one;
            two = one;
            one = sum;
        }
        return sum;
    }
}
