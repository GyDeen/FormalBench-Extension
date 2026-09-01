
import java.io.*;
import java.lang.*;
import java.math.*;

class IsProductEven {

	public static Boolean isProductEven(int[] arr, int n) {
		int product = 1;
		for (int i = 0; arr.length > i; i++) {
			product *= arr[i];
		}
		return product % n == 0;
	}
}
