
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ZipTuples {
    
    public static int[][] zipTuples(int[] testTup1, int[] testTup2) {
        int numRows = testTup1.length;
        int[][] res = new int[numRows][2];

        for (int i = 0; i < numRows; i++) {
            res[i][0] = testTup1[i];
            res[i][1] = testTup2[i % testTup2.length];
        }
        return res;
    }
}

