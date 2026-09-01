
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SplitList {
    
    public static String[] splitList(String text) {
        String[] words = text.split("(?=\\p{Upper})");

        return words;
    }
}

