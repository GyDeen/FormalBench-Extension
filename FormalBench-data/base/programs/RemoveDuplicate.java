import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class RemoveDuplicate {
    
    public static String removeDuplicate(String string) {
        Set<String> set = new HashSet<>();
        String[] words = string.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (set.add(word)) {
                sb.append(word).append(" ");
            }
        }
        return sb.toString().trim();
    }
}

