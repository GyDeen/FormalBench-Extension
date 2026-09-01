import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ApSum {

	public static int apSum(int a, int n, int d) {
		int sum = 0;
		int max = 0;
		while (max < n) {
			sum += (a + max * d);
			max++;
		}
		return sum;
	}
}
