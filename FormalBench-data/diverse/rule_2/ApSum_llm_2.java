import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ApSum {

	public static int apSum(int a, int n, int d) {
		int sum = 0;
		int result = 0;
		while (result < n) {
			sum += (a + result * d);
			result++;
		}
		return sum;
	}
}
