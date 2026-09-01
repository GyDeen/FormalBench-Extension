
import java.io.*;
import java.lang.*;
import java.util.*;

class BitwiseXor {
    
    public static int[] bitwiseXor(int[] testTup1, int[] testTup2) {
        int length = testTup1.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = testTup1[i] ^ testTup2[i];
        }
        return result;
    }
}

