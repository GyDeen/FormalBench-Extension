
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLongWord {
    
    public static String[] findLongWord(String text) {
        String regex = "\\b(\\w{5})\\b";
        String[] words = text.split(" ");
        String[] tempResult = new String[words.length];
        int count = 0;

        for (String word : words) {
            if (word.matches(regex)) {
                tempResult[count++] = word;
            }
        }

        String[] result = new String[count];
        System.arraycopy(tempResult, 0, result, 0, count);

        return result;
    }
}

