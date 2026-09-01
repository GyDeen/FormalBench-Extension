
import java.io.*;
import java.lang.*;
import java.util.Arrays;

class Lcopy {
    
    public static int[] lcopy(int[] xs) {
        int[] res = new int[xs.length];
        System.arraycopy(xs, 0, res, 0, xs.length);
        return res;
    }
}

