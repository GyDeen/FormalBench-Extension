import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CheckSubstring {
    
    public static String checkSubstring(String string, String sample) {
        String regex = "^" + sample + ".*";
        return string.matches(regex) ? "string starts with the given substring" : "string doesnt start with the given substring";
    }
}

