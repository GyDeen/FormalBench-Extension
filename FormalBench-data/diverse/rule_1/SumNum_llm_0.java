
import java.io.*;
import java.lang.*;
import java.math.*;

class SumNum {

	public static Double sumNum(int[] values) {
		double result = 0;
		for (int i : values) {
			result += i;
		}
		return result / values.length;
	}
}
