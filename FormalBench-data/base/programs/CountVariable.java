
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountVariable {
    
    public static String[] countVariable(int a, int b, int c, int d) {
        int totalSize = Math.max(0, a) + Math.max(0, b) + Math.max(0, c) + Math.max(0, d);
        String[] array = new String[totalSize];
        int index = 0;

        for (int i = 0; i < a; i++) {
            array[index++] = "p";
        }
        for (int i = 0; i < b; i++) {
            array[index++] = "q";
        }
        for (int i = 0; i < c; i++) {
            array[index++] = "r";
        }
        for (int i = 0; i < d; i++) {
            array[index++] = "s";
        }

        return array;
    }
}

