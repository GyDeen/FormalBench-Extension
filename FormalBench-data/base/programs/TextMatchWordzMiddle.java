import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class TextMatchWordzMiddle {
    
    public static String textMatchWordzMiddle(String text) {
        String result = "Not matched!";
        if (text.contains("z")) {
            int index = text.indexOf("z");
            if (index != 0 && index != text.length() - 1) {
                result = "Found a match!";
            }
        }
        return result;
    }
}

