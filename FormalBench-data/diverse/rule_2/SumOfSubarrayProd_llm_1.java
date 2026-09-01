
import java.io.*;
import java.lang.*;
import java.math.*;

class SumOfSubarrayProd {

	public static int sumOfSubarrayProd(int[] arr, int n) {
		int sum = 0;
		int count = 0;
		while (count < n) {
			int product = 1;
			for (int j = count; j < n; j++) {
				product *= arr[j];
				sum += product;
			}
			count++;
		}
		return sum;
	}
}
