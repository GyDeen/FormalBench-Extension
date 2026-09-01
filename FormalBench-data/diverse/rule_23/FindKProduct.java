
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindKProduct {

	public static int findKProduct(int[][] testArray, int k) {
		int product = 1;
		int n = testArray.length;
		for (int i = 0; n > i; i++) {
			product *= testArray[i][k];
		}
		return product;
	}
}
