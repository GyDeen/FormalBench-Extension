import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ApSum {

	public static int apSum(int a, int n, int d) {
		int sum = 0;
		int total = 0;
		while (total < n) {
			sum += (a + total * d);
			total++;
		}
		return sum;
	}
}
