import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class IsUndulating {
    
    public static Boolean isUndulating(String n) {
        if(n.length()%2 != 0)
            return true;
        int i = 0;
        while(i < n.length()) {
            char ch = n.charAt(i);
            char ch2 = n.charAt(i+1);
            if(ch == ch2)
                i++;
            else
                return false;
        }
        return true;
    }
}

