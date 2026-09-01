import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class TextMatchWord {
    
    public static String textMatchWord(String text) {
        String result = "Not matched!";
        if (text.endsWith("python.") || text.endsWith("python") || text.endsWith("python ")) {
            result = "Found a match!";
        }
        return result;
    }
}

