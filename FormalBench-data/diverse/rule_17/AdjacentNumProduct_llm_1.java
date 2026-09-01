
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

		int min = nums.length - 1;
		for (int i = 0; i < min; i++) {
			int current = i + 1;
			currentProduct = nums[i] * nums[current];
			if (currentProduct > maxProduct) {
				maxProduct = currentProduct;
			}
		}
		return maxProduct;
	}
}
