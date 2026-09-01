
import java.io.*;
import java.lang.*;
import java.math.*;

class MultiplyNum {

	public static Double multiplyNum(int[] numbers) {
		double result = 1.0;
		for (int i = 0; i < numbers.length; i++) {
			result = result * (numbers[i]);
		}
		return result / numbers.length;
	}
}
