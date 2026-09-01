import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CheckVow {
    
    public static int checkVow(String string, String vowels) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            if (vowels.indexOf(ch) != -1) {
                count++;
            }
        }
        return count;
    }
}

