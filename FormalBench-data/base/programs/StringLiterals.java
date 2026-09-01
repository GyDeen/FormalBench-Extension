
import java.io.*;
import java.lang.*;
import java.math.*;


class StringLiterals {
    
    public static String stringLiterals(String[] patterns, String text) {
        String result = "Not Matched!";
        for (String pattern : patterns) {
            if (text.contains(pattern)) {
                result = "Matched!";
                break;
            }
        }
        return result;
    }
}

