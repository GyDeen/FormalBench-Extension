
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

		int min = 0;
		while (min < nums.length - 1) {
			currentProduct = nums[min] * nums[min + 1];
			if (currentProduct > maxProduct) {
				maxProduct = currentProduct;
			}
			min++;
		}
		return maxProduct;
	}
}
