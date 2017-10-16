package chap4_6;

public class Stringwastetime {
    public static void main(String[] args) {
        long beforeTime = System.currentTimeMillis();
        //StringTest();
        //StringBufferTest();
        StringBuilderTest();
        long afterTime = System.currentTimeMillis();
        System.out.println("time: " + (afterTime - beforeTime));
    }

    private static void StringTest() {

        String s = "abcdefg";
        for (int i = 0; i < 10000; i++) {
            s += "abcdefg";
        }
    }

    private static void StringBufferTest() {

        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < 10000; i++) {
            stringBuffer = stringBuffer
                    .append("abcdefg");
        }
    }

    private static void StringBuilderTest() {

        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < 10000; i++) {
            stringBuilder = stringBuilder
                    .append("abcdefg");
        }
    }
}