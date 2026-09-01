
import java.io.*;
import java.lang.*;
import java.math.*;

class IsProductEven {

	public static Boolean isProductEven(int[] arr, int n) {
		int product = 1;
		int prod = 0;
		while (prod < arr.length) {
			product *= arr[prod];
			prod++;
		}
		return product % n == 0;
	}
}
