
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindKProduct {

	public static int findKProduct(int[][] testArray, int k) {
		int product = 1;
		int n = testArray.length;
		int p = 0;
		while (p < n) {
			product *= testArray[p][k];
			p++;
		}
		return product;
	}
}
