import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class SetLeftMostUnsetBit {
    
    public static int setLeftMostUnsetBit(int n) {
        int pos, temp, count = 0;
        if ((n & (n + 1)) == 0) {
            return n;
        }
        pos = 0;
        temp = n;
        while (temp != 0) {
            if ((temp & 1) == 0) {
                pos = count;
            }
            count++;
            temp = temp >> 1;
        }
        return n | (1 << pos);
    }
}

