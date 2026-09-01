import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CamelToSnake {
    
    public static String camelToSnake(String text) {
        String[] words = text.split("(?=[A-Z])");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word.toLowerCase());
            sb.append("_");
        }
        return sb.substring(0, sb.length() - 1);
    }
}

