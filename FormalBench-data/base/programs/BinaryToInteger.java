
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinaryToInteger {
    
    public static String binaryToInteger(int[] testTup) {
        int sum = 0;
        for (int i = 0; i < testTup.length; i++) {
            sum = (sum << 1) | testTup[i];
        }
        return Integer.toString(sum);
    }
}

