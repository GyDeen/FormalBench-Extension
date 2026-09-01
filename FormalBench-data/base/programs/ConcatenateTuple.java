
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateTuple {
    
    public static String concatenateTuple(Object[] testTup) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < testTup.length; i++) {
            result.append(testTup[i]);
            if (i < testTup.length - 1) {
                result.append("-");
            }
        }
        return result.toString();
    }
}

