
import java.io.*;
import java.lang.*;
import java.math.*;

class SumOfSubarrayProd {

	public static int sumOfSubarrayProd(int[] arr, int n) {
		int sum = 0;
		int tmp = 0;
		while (tmp < n) {
			int product = 1;
			for (int j = tmp; j < n; j++) {
				product *= arr[j];
				sum += product;
			}
			tmp++;
		}
		return sum;
	}
}
