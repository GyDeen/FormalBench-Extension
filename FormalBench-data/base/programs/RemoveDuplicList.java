
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveDuplicArray {

    
    public static String[] removeDuplicList(String[] arr) {
        Set<String> set = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (String s : arr) {
            if (!set.contains(s)) {
                set.add(s);
                result.add(s);
            }
        }
        return result.toArray(new String[0]);
    }
}

