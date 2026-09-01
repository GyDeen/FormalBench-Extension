
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SpecifiedElement {

	public static int[] specifiedElement(int[][] nums, int n) {
		int numRows = nums.length;
		int[] result = new int[numRows];

		int offset = 0;
		while (offset < numRows) {
			result[offset] = nums[offset][n];
			offset++;
		}

		return result;
	}
}
