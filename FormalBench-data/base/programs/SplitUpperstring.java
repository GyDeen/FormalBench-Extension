import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class SplitUpperstring {
    
    public static List<String> splitUpperstring(String text) {
        List<String> result = new ArrayList<>();
        String[] words = text.split("(?=\\p{Upper})");
        for (String word : words) {
            result.add(word);
        }
        return result;
    }
}

