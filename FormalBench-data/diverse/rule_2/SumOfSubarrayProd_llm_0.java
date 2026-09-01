
import java.io.*;
import java.lang.*;
import java.math.*;

class SumOfSubarrayProd {

	public static int sumOfSubarrayProd(int[] arr, int n) {
		int sum = 0;
		int total = 0;
		while (total < n) {
			int product = 1;
			for (int j = total; j < n; j++) {
				product *= arr[j];
				sum += product;
			}
			total++;
		}
		return sum;
	}
}
