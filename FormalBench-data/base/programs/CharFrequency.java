import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CharFrequency {
    
    public static HashMap<String, Integer> charFrequency(String str1) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            if (map.containsKey(str1.charAt(i) + "")) {
                map.put(str1.charAt(i) + "", map.get(str1.charAt(i) + "") + 1);
            } else {
                map.put(str1.charAt(i) + "", 1);
            }
        }
        return map;
    }
}

