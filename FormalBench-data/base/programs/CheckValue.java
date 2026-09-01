import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CheckValue {
    
    public static Boolean checkValue(HashMap<String, Integer> dict, int n) {
        for (String key : dict.keySet()) {
            if (dict.get(key) != n) {
                return false;
            }
        }
        return true;
    }
}

