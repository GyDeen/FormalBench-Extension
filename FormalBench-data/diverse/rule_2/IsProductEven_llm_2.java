
import java.io.*;
import java.lang.*;
import java.math.*;

class IsProductEven {

	public static Boolean isProductEven(int[] arr, int n) {
		int product = 1;
		int test = 0;
		while (test < arr.length) {
			product *= arr[test];
			test++;
		}
		return product % n == 0;
	}
}
