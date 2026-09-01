
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SpecifiedElement {

	public static int[] specifiedElement(int[][] nums, int n) {
		int numRows = nums.length;
		int[] result = new int[numRows];

		int num = 0;
		while (num < numRows) {
			result[num] = nums[num][n];
			num++;
		}

		return result;
	}
}
