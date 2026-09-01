
import java.io.*;
import java.lang.*;
import java.math.*;

class IsProductEven {

	public static Boolean isProductEven(int[] arr, int n) {
		int product = 1;
		for (int i = 0; i < arr.length; i++) {
			product *= arr[i];
		}
		int prod = product % n;
		return prod == 0;
	}
}
