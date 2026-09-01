
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindKProduct {

	public static int findKProduct(int[][] testArray, int k) {
		int product = 1;
		int n = testArray.length;
		int r = 0;
		while (r < n) {
			product *= testArray[r][k];
			r++;
		}
		return product;
	}
}
