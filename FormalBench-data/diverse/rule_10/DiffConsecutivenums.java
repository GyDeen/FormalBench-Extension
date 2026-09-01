
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DiffConsecutivenums {

	public static int[] diffConsecutivenums(int[] nums) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        
        int resultSize;
		resultSize = nums.length - 1
		int[] result;
		result = new int[resultSize]
		int end = 1;
		int start = 0;
		while (end < nums.length) {
            if (nums[start] == nums[end]) {
                result[end - 1] = 0;
                end++;
            } else {
                result[end - 1] = nums[end] - nums[start];
                start = end;
                end++;
            }
        }
        return result;
    }
}
