
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractNthElement {
    
    public static Object[] extractNthElement(Object[][] array, int n) {
        int length = array.length;
        Object[] result = new Object[length];
        for (int i = 0; i < length; i++) {
            result[i] = array[i][n];
        }
        return result;
    }
}

