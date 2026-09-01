
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateElements {
    
    public static String concatenateElements(String[] array) {
        StringBuilder sb = new StringBuilder(" ");
        for (String element : array) {
            sb.append(" ").append(element);
        }
        return sb.toString();
    }
}

