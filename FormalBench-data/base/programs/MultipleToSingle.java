
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultipleToSingle {
    
    public static int multipleToSingle(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num);
        }
        return Integer.parseInt(sb.toString());
    }
}

