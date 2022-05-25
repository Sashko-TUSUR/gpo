package com.dreamteam.TestingSystemNew.payload;


public class СonstructorParam {

    static final class MyResult {
        private final int znam;
        private final int chislitel;


        public MyResult(int first, int second) {
            this.znam = first;
            this.chislitel = second;
        }

        public int getFirst() {
            return znam;
        }

        public int getSecond() {
            return chislitel;
        }
    }


    public static MyResult something() {
        final int min = 2; // Минимальное число для диапазона
        final int max = 9; // Максимальное число для диапазона
        int chisl = chisl(min, max);
        final int znam = znam(min, max);
        while(znam<=chisl)
        {
            chisl =--chisl;
        }
        final int chislitel = chisl;

        return new MyResult(znam, chislitel);
    }

    private static int znam(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public static int chisl(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
