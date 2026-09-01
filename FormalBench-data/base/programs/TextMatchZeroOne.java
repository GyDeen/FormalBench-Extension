import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class TextMatchZeroOne {
    
    public static String textMatchZeroOne(String text) {
        if(text.matches(".*[ab]{1}.*")){
            return "Found a match!";
        }else{
            return "Not matched!";
        }
    }
}

