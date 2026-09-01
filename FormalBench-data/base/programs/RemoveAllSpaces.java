import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class RemoveAllSpaces {
    
    public static String removeAllSpaces(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ') {
                sb.append(text.charAt(i));
            }
        }
        return sb.toString();
    }
}

