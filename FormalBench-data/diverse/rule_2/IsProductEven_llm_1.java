
import java.io.*;
import java.lang.*;
import java.math.*;

class IsProductEven {

	public static Boolean isProductEven(int[] arr, int n) {
		int product = 1;
		int result = 0;
		while (result < arr.length) {
			product *= arr[result];
			result++;
		}
		return product % n == 0;
	}
}
