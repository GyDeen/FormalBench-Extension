
import java.io.*;
import java.lang.*;
import java.math.*;

class SumOfSubarrayProd {

	public static int sumOfSubarrayProd(int[] arr, int n) {
		int sum = 0;
		for (int i = 0; n > i; i++) {
			int product = 1;
			for (int j = i; n > j; j++) {
				product *= arr[j];
				sum += product;
			}
		}
		return sum;
	}
}
