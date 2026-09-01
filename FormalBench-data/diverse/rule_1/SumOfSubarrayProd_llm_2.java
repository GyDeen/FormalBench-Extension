
import java.io.*;
import java.lang.*;
import java.math.*;

class SumOfSubarrayProd {

	public static int sumOfSubarrayProd(int[] sub, int num) {
		int sum = 0;
		for (int count = 0; count < num; count++) {
			int tmp = 1;
			for (int p = count; p < num; p++) {
				tmp *= sub[p];
				sum += tmp;
			}
		}
		return sum;
	}
}
