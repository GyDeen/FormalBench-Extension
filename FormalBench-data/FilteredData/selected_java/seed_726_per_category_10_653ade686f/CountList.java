
import java.io.*;
import java.lang.*;
import java.math.*;

class CountList {
    
    public static int countList(int[][] inputArray) {
        int count = 0;
        for (int[] array : inputArray) {
            if (array.length > 0) {
                count++;
            }
        }
        return count;
    }
}

