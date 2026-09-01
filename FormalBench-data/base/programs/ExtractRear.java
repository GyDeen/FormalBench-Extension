
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractRear {
    
    public static String[] extractRear(String[] testTuple) {
        String[] result = new String[testTuple.length];
        for (int i = 0; i < testTuple.length; i++) {
            result[i] = testTuple[i].substring(testTuple[i].length() - 1);
        }
        return result;
    }
}

