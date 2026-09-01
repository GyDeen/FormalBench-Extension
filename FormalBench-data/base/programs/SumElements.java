
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumElements {
    
    public static int sumElements(int[] testTup) {
        int sum = 0;
        for (int value : testTup) {
            sum += value;
        }
        return sum;
    }
}

