import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class NoOfCubes {
    
    public static int noOfCubes(int n, int k) {
        int no = 0;
        no = (n - k + 1) * (n - k + 1) * (n - k + 1);
        return no;
    }
}

