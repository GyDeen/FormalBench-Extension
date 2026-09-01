import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class RemoveChar {
    
    public static String removeChar(String s) {
        return s.replaceAll("[^a-zA-Z0-9]", "");
    }
}

