import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class AsciiValueString {
    
    public static int asciiValueString(String k) {
        int asciiValue = 0;
        for (int i = 0; i < k.length(); i++) {
            asciiValue += (int) k.charAt(i);
        }
        return asciiValue;
    }
}

