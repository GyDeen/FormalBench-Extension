
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Remove {
    
    public static String[] remove(String[] arr) {
        String[] result = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i].replaceAll("\\d", "");
        }
        return result;
    }
}

