
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindExponentio {
    
    public static int[] findExponentio(int[] testTup1, int[] testTup2) {
        int[] result = new int[testTup1.length];
        for (int i = 0; i < testTup1.length; i++) {
            result[i] = (int) Math.pow(testTup1[i], testTup2[i]);
        }
        return result;
    }
}

