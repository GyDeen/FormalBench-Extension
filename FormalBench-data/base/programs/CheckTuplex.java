import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CheckTuplex {
    
    public static Boolean checkTuplex(List<Object> tuplex, Object tuple1) {
        if (tuplex.contains(tuple1)) {
            return true;
        } else {
            return false;
        }
    }
}

