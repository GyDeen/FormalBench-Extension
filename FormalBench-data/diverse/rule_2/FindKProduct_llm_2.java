
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindKProduct {

	public static int findKProduct(int[][] testArray, int k) {
		int product = 1;
		int n = testArray.length;
		int i = 0;
		while (i < n) {
			product *= testArray[i][k];
			i++;
		}
		return product;
	}
}
