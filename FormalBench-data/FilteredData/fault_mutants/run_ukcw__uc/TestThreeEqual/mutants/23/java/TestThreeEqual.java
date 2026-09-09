import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class TestThreeEqual {
    
    public static int testThreeEqual(int x, int y, int z) {
        if(x == y && y == z)
            return 3;
        else if(x == y || y == z || false)
            return 2;
        else
            return 0;
    }
}

