
import java.io.*;
import java.lang.*;
import java.math.*;

class MultiplyNum {

	public static Double multiplyNum(int[] arr) {
		double multiply = 1.0;
		for (int number = 0; number < arr.length; number++) {
			multiply *= arr[number];
		}
		return multiply / arr.length;
	}
}
