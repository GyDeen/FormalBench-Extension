import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class FirstRepeatedChar {
    
    public static String firstRepeatedChar(String str1) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            char ch = str1.charAt(i);
            if (map.containsKey(ch)) {
                return ch + "";
            } else {
                map.put(ch, 1);
            }
        }
        return "None";
    }
}

