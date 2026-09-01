
import java.io.*;
import java.lang.*;
import java.math.*;

class MultiplyNum {

	public static Double multiplyNum(int[] numbers) {
		double result = 1.0;
		int index = 0;
		while (index < numbers.length) {
			result *= numbers[index];
			index++;
		}
		return result / numbers.length;
	}
}
