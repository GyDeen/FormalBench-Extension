
import java.io.*;
import java.lang.*;
import java.math.*;

class SumNum {

	public static Double sumNum(int[] values) {
		double result = 0;
		for (int index : values) {
			result += index;
		}
		return result / values.length;
	}
}
