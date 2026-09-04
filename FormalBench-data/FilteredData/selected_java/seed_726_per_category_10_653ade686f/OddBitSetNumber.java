import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class OddBitSetNumber {
    
    public static int oddBitSetNumber(int n) {
        int n_ = n & 0xFFFFFFFF;
        n_ |= (n & 0xAAAAAAAA) >>> 1;
        n_ |= (n & 0xCCCCCCCC) >>> 2;
        n_ |= (n & 0xF0F0F0F0) >>> 4;
        n_ |= (n & 0xFF00FF00) >>> 8;
        n_ |= (n & 0xFFFF0000) >>> 16;
        return n_;
    }
}

