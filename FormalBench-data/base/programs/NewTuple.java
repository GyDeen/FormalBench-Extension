
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NewTuple {
    
    public static String[] newTuple(String[] testArray, String testStr) {
        String[] newArray = new String[testArray.length + 1];
        for (int i = 0; i < testArray.length; i++) {
            newArray[i] = testArray[i];
        }
        newArray[testArray.length] = testStr;
        return newArray;
    }
}

