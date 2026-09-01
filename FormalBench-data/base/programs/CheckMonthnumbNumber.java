import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class CheckMonthnumbNumber {
    
    public static Boolean checkMonthnumbNumber(int monthnum2) {
        if (monthnum2 == 1 || monthnum2 == 3 || monthnum2 == 5 || monthnum2 == 7 || monthnum2 == 8 || monthnum2 == 10 || monthnum2 == 12) {
            return true;
        }
        return false;
    }
}

