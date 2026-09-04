
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplyElements {
    
    public static int[] multiplyElements(int[] testTup) {
        if (testTup.length < 2) {
            return new int[0]; // return empty array if input has less than 2 elements
        }
        
        int[] result = new int[testTup.length - 1];
        for (int i = 0; i < testTup.length - 1; i++) {
            result[i] = testTup[i] * testTup[i + 1];
        }
        return result;
    }
}

