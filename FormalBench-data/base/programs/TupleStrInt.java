
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class TupleStrInt {
    
    public static int[] tupleStrInt(String testStr) {
        String[] tupleParts = testStr.substring(1, testStr.length() - 1).split(", ");
        int[] tupleArray = new int[tupleParts.length];

        for (int i = 0; i < tupleParts.length; i++) {
            tupleArray[i] = Integer.parseInt(tupleParts[i]);
        }
        return tupleArray;
    }
}

