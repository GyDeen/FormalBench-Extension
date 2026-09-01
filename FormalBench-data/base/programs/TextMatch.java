import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class TextMatch {
    
    public static String textMatch(String text) {
        String regex = "a(\\w+)\\1*";
        if (text.matches(regex)) {
            return "Found a match!";
        } else {
            return "Not matched!";
        }
    }
}

