
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindKProduct {

	public static int findKProduct(int[][] testArray, int k) {
		int n = testArray.length;
		int product = 1;
		for (int i = 0; i < n; i++) {
			product *= testArray[i][k];
		}
		return product;
	}
}
