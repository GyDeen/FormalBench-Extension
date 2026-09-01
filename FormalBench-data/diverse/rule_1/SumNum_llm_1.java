
import java.io.*;
import java.lang.*;
import java.math.*;

class SumNum {

	public static Double sumNum(int[] values) {
		double total = 0;
		for (int i : values) {
			total += i;
		}
		return total / values.length;
	}
}
