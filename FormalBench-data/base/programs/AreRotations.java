import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class AreRotations {
    
    public static Boolean areRotations(String string1, String string2) {
        if (string1.length() != string2.length()) {
            return false;
        }
        String s1 = string1 + string1;
        return s1.contains(string2);
    }
}

