
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SquareNums {
    
    public static int[] squareNums(int[] nums) {
        int[] squares = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            squares[i] = nums[i] * nums[i];
        }
        return squares;
    }
}

