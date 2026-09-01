import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class RemoveDirtyChars {
    
    public static String removeDirtyChars(String string, String secondString) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (!secondString.contains(string.charAt(i) + "")) {
                sb.append(string.charAt(i));
            }
        }
        return sb.toString();
    }
}

