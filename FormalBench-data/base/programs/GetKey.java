import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class GetKey {
    
    public static List<Integer> getKey(HashMap<Integer, String> dict) {
        Set<Integer> keyset = dict.keySet();
        List<Integer> keys = new ArrayList<Integer>(keyset);
        Collections.sort(keys);
        return keys;
    }
}

