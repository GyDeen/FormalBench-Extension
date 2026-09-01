import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CheckInteger {
    
    public static Boolean checkInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

