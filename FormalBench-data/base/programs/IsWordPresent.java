import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class IsWordPresent {
    
    public static Boolean isWordPresent(String sentence, String word) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word)) {
                return true;
            }
        }
        return false;
    }
}

