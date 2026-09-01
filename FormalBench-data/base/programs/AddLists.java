
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddLists {
    
    public static int[] addLists(int[] testList, int[] testTup) {
        int[] res = new int[testList.length + testTup.length];
        
        System.arraycopy(testTup, 0, res, 0, testTup.length);
        
        System.arraycopy(testList, 0, res, testTup.length, testList.length);
        
        return res;
    }
}

