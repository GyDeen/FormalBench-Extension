
import java.io.*;
import java.lang.*;

class TupString {
    
    public static String tupString(String[] tup1) {
        StringBuilder sb = new StringBuilder();
        for (String s : tup1) {
            sb.append(s);
        }
        return sb.toString();
    }
}

