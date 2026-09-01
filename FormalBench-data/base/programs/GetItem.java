
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetItem {
    
    public static String getItem(Object[] tup1, int index) {
        if (index < 0) {
            index = tup1.length + index;
        }
        return (String) tup1[index];
    }
}

