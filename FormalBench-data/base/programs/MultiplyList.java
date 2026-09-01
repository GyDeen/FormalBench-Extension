
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplyArray {
    
    public static int multiplyList(int[] items) {
        int result = 1;
        for (int item : items) {
            result *= item;
        }
        return result;
    }
}

