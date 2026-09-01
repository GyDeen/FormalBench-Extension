
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddTuple {
    
    public static int[] addTuple(int[] testList, int[] testTup) {
        int[] output = new int[testList.length + testTup.length];
        System.arraycopy(testList, 0, output, 0, testList.length);
        System.arraycopy(testTup, 0, output, testList.length, testTup.length);
        return output;
    }
}

