import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CheckMonthnumber {
    
    public static Boolean checkMonthnumber(String monthname3) {
        if (monthname3.equals("April") || monthname3.equals("June") || monthname3.equals("September") || monthname3.equals("November")) {
            return true;
        } else {
            return false;
        }
    }
}

