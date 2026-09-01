
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AdjacentNumProduct {

	public static int adjacentNumProduct(int[] nums) {
		if (nums.length < 2) {
			throw new IllegalArgumentException("Array must contain at least two elements");
		}

		int maxProduct = 0;
		int currentProduct = 1;

		int next = 0;
		while (next < nums.length - 1) {
			currentProduct = nums[next] * nums[next + 1];
			if (currentProduct > maxProduct) {
				maxProduct = currentProduct;
			}
			next++;
		}
		return maxProduct;
	}
}
