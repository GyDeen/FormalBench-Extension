import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class ReplaceSpecialchar {
    
    public static String replaceSpecialchar(String text) {
        String[] arr = text.split(",");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].replaceAll(" ", ":");
            arr[i] = arr[i].replaceAll("\\.", ":");
        }
        return String.join(":", arr);
    }
}

