import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class RemoveMultipleSpaces {
    
    public static String removeMultipleSpaces(String text1) {
        return text1.replaceAll("\\s+", " ");
    }
}

