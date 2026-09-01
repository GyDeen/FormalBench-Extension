
import java.io.*;
import java.lang.*;
import java.math.*;

class MultiplyNum {

	public static Double multiplyNum(int[] arr) {
		double number = 1.0;
		for (int index = 0; index < arr.length; index++) {
			number *= arr[index];
		}
		return number / arr.length;
	}
}
