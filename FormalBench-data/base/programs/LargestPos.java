
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargestPos {
    
    public static int largestPos(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0 && array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}

