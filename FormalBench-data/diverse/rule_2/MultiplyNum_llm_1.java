
import java.io.*;
import java.lang.*;
import java.math.*;

class MultiplyNum {

	public static Double multiplyNum(int[] numbers) {
		double result = 1.0;
		int start = 0;
		while (start < numbers.length) {
			result *= numbers[start];
			start++;
		}
		return result / numbers.length;
	}
}
