import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class MaxOfThree {
    
    public static int maxOfThree(int num1, int num2, int num3) {
        int max = num1 > num2 ? num1 : num2;
        return max > num3 ? max : num3;
    }
}

