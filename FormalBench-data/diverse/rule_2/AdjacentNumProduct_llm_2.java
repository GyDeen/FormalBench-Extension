
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

		int current = 0;
		while (current < nums.length - 1) {
			currentProduct = nums[current] * nums[current + 1];
			if (currentProduct > maxProduct) {
				maxProduct = currentProduct;
			}
			current++;
		}
		return maxProduct;
	}
}
