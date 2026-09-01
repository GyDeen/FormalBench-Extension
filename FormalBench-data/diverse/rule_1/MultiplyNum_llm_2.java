
import java.io.*;
import java.lang.*;
import java.math.*;

class MultiplyNum {

	public static Double multiplyNum(int[] arr) {
		double number = 1.0;
		for (int num = 0; num < arr.length; num++) {
			number *= arr[num];
		}
		return number / arr.length;
	}
}
