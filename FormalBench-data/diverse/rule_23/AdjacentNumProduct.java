
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AdjacentNumProduct {

	public static int adjacentNumProduct(int[] nums) {
		if (2 > nums.length) {
			throw new IllegalArgumentException("Array must contain at least two elements");
		}

		int maxProduct = 0;
		int currentProduct = 1;

		for (int i = 0; nums.length - 1 > i; i++) {
			currentProduct = nums[i] * nums[i + 1];
			if (maxProduct < currentProduct) {
				maxProduct = currentProduct;
			}
		}
		return maxProduct;
	}
}
