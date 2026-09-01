import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class HighestPowerOf2 {
    
    public static int highestPowerOf2(int n) {
        int power = 1;
        while (power <= n) {
            power *= 2;
        }
        return power / 2;
    }
}

