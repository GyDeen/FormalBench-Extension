
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortSublists {
    
    public static String[][] sortSublists(String[][] arrays) {
        String[][] sortedArrays = new String[arrays.length][];
        
        for (int i = 0; i < arrays.length; i++) {
            String[] sublist = arrays[i];
            Arrays.sort(sublist);
            sortedArrays[i] = sublist;
        }
        
        return sortedArrays;
    }
}

