
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateNested {
    
    public static int[] concatenateNested(int[] testTup1, int[] testTup2) {
        int[] result = new int[testTup1.length + testTup2.length];
        
        System.arraycopy(testTup1, 0, result, 0, testTup1.length);
        
        System.arraycopy(testTup2, 0, result, testTup1.length, testTup2.length);
        
        return result;
    }
}

