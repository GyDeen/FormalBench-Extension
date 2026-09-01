
import java.io.*;
import java.lang.*;
import java.math.*;

class SumOfSubarrayProd {

	public static int sumOfSubarrayProd(int[] arr, int n) {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			int product = 1;
			for (int j = i; j < n; j++) {
				product = product * (arr[j]);
				sum = sum + product;
			}
		}
		return sum;
	}
}
