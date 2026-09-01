import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class MergeDictionaries {
    
    public static HashMap<String, String> mergeDictionaries(HashMap<String, String> dict1, HashMap<String, String> dict2) {
        HashMap<String, String> merged = new HashMap<>();
        for (String key : dict1.keySet()) {
            merged.put(key, dict1.get(key));
        }
        for (String key : dict2.keySet()) {
            merged.put(key, dict2.get(key));
        }
        return merged;
    }
}

