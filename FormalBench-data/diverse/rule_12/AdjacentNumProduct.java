
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AdjacentNumProduct {

	public static int adjacentNumProduct(int[] nums) {
		int maxProduct = 0;

		if (nums.length < 2) {
			throw new IllegalArgumentException("Array must contain at least two elements");
		}
		int currentProduct = 1;

		for (int i = 0; i < nums.length - 1; i++) {
			currentProduct = nums[i] * nums[i + 1];
			if (currentProduct > maxProduct) {
				maxProduct = currentProduct;
			}
		}
		return maxProduct;
	}
}
