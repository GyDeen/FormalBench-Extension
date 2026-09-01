
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleToSet {
    
    public static HashSet<String> tupleToSet(String[] t) {
        HashSet<String> set = new HashSet<>();
        for (String s : t) {
            set.add(s);
        }
        return set;
    }
}

