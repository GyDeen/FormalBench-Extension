import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CheckStr {
    
    public static String checkStr(String string) {
        String regex = "^[aeiouAEIOU].*";
        return string.matches(regex) ? "Valid" : "Invalid";
    }
}

