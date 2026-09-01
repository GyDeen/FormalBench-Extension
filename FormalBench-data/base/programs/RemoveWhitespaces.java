import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class RemoveWhitespaces {
    
    public static String removeWhitespaces(String text1) {
        String text = text1.replaceAll("\\s+", "");
        return text;
    }
}

