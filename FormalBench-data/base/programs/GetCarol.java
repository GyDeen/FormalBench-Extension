import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class GetCarol {
    
    public static int getCarol(int n) {
        return (int) (Math.pow(2, n) - 1) * (int) (Math.pow(2, n) - 1) - 2;
    }
}

