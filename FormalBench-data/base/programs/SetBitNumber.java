import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class SetBitNumber {
    
    public static int setBitNumber(int n) {
        return (int)Math.pow(2, (int)(Math.log(n)/Math.log(2)));
    }
}

