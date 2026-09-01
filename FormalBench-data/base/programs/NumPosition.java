import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class NumPosition {
    
    public static int numPosition(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
}

