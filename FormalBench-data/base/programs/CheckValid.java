
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CheckValid {
    
    public static Boolean checkValid(boolean[] testTup) {
        for (boolean value : testTup) {
            if (!value) {
                return false;
            }
        }
        return true;
    }
}

