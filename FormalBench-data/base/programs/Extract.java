
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Extract {
    
    public static int[] extract(int[][] lst) {
        int[] result = new int[lst.length];
        for (int i = 0; i < lst.length; i++) {
            result[i] = lst[i][0]; 
        }
        return result;
    }
}

