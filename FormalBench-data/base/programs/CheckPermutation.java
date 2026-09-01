import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CheckPermutation {
    
    public static Boolean checkPermutation(String str1, String str2) {
        int[] letters = new int[26];
        for (int i = 0; i < str1.length(); i++) {
            letters[str1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < str2.length(); i++) {
            letters[str2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != 0) {
                return false;
            }
        }
        return true;
    }
}

