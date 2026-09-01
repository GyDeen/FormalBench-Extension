import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class MergeDict {
    
    public static HashMap<String, Integer> mergeDict(HashMap<String, Integer> d1, HashMap<String, Integer> d2) {
        HashMap<String, Integer> result = new HashMap<>();
        for (String key : d1.keySet()) {
            result.put(key, d1.get(key));
        }
        for (String key : d2.keySet()) {
            result.put(key, d2.get(key));
        }
        return result;
    }
}

