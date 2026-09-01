import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CountOccurance {
    
    public static int countOccurance(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 's' && s.charAt(i + 1) == 't' && s.charAt(i + 2) == 'd') {
                count++;
            }
        }
        return count;
    }
}

