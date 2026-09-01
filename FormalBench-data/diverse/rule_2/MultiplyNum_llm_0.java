
import java.io.*;
import java.lang.*;
import java.math.*;

class MultiplyNum {

	public static Double multiplyNum(int[] numbers) {
		double result = 1.0;
		int i = 0;
		while (i < numbers.length) {
			result *= numbers[i];
			i++;
		}
		return result / numbers.length;
	}
}
