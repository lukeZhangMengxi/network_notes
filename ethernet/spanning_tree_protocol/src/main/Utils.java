package main;

public class Utils {
    public static boolean leftIsSmaller(byte[] left, byte[] right) {
        // assert left.length == right.length
        for (int i=0; i<left.length; i++) {
            if (left[i] < right[i]) return true;
            else if (left[i] > right[i]) return false;
        }
        return false;
    }

    public static int twoBytesToInt(byte[] bytes) {
        // assert bytes.length == 2
        return bytes[0] << 8 + bytes[1];
    }
}
