
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SpecifiedElement {

	public static int[] specifiedElement(int[][] nums, int n) {
		int numRows = nums.length;
		int[] result = new int[numRows];

		int i = 0;
		while (i < numRows) {
			result[i] = nums[i][n];
			i++;
		}

		return result;
	}
}
