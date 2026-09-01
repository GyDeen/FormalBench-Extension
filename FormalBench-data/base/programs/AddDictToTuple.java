import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class AddDictToTuple {
    
    public static List<Object> addDictToTuple(List<Integer> testTup, HashMap<String, Integer> testDict) {
        List<Object> res = new ArrayList<>();
        for (Integer i : testTup) {
            res.add(i);
        }
        res.add(testDict);
        return res;
    }
}

