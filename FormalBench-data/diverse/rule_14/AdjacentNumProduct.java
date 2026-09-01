
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AdjacentNumProduct {

	public static int adjacentNumProduct(int[] nums) {
		if (!(nums.length < 2))
			;
		else {
			throw new IllegalArgumentException("Array must contain at least two elements");
		}

		int maxProduct = 0;
		int currentProduct = 1;

		for (int i = 0; i < nums.length - 1; i++) {
			currentProduct = nums[i] * nums[i + 1];
			if (!(currentProduct > maxProduct))
				;
			else {
				maxProduct = currentProduct;
			}
		}
		return maxProduct;
	}
}
