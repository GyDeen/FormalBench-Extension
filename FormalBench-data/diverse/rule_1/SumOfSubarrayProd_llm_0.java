
import java.io.*;
import java.lang.*;
import java.math.*;

class SumOfSubarrayProd {

	public static int sumOfSubarrayProd(int[] sub, int num) {
		int sum = 0;
		for (int count = 0; count < num; count++) {
			int tmp = 1;
			for (int prod = count; prod < num; prod++) {
				tmp *= sub[prod];
				sum += tmp;
			}
		}
		return sum;
	}
}
