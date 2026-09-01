
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleIntStr {
    
    public static int[][] tupleIntStr(String[][] tupleStr) {
        int[][] tupleInt = new int[tupleStr.length][];
        
        for (int i = 0; i < tupleStr.length; i++) {
            tupleInt[i] = new int[tupleStr[i].length];
            for (int j = 0; j < tupleStr[i].length; j++) {
                tupleInt[i][j] = Integer.parseInt(tupleStr[i][j]);
            }
        }
        
        return tupleInt;
    }
}

