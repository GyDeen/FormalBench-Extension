
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class FlattenTuple {
    
    public static String flattenTuple(String[][] testArray) {
        StringBuilder sb = new StringBuilder();
        for (String[] array : testArray) {
            for (String str : array) {
                sb.append(str).append(" ");
            }
        }
        return sb.toString().trim();
    }
}

