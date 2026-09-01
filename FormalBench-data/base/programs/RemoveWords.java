
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveWords {
    
    public static String[] removeWords(String[] list1, String[] removeWords) {
        Set<String> wordsToRemove = new HashSet<>(Arrays.asList(removeWords));
        List<String> resultList = new ArrayList<>();
        
        for (String s : list1) {
            if (!wordsToRemove.contains(s)) {
                resultList.add(s);
            }
        }
        
        return resultList.toArray(new String[0]);
    }
}

