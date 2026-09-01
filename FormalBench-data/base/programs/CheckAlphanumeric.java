import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CheckAlphanumeric {
    
    public static String checkAlphanumeric(String string) {
        return string.matches("^[a-zA-Z0-9]*$") ? "Accept" : "Discard";
    }
}

