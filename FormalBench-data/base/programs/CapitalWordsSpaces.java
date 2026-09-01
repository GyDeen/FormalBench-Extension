import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CapitalWordsSpaces {
    
    public static String capitalWordsSpaces(String str1) {
        String str = str1.replaceAll("([A-Z])", " $1");
        return str.trim();
    }
}

