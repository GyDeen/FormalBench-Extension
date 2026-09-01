import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class LowerCtr {
    
    public static int lowerCtr(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLowerCase(str.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}

