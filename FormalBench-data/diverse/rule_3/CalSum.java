import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CalSum {

	public static int calSum(int n) {
		if (n == 0) {
			return 3;
		}
		if (n == 1) {
			return 3;
		}
		if (n == 2) {
			return 5;
		}
		int sum = 5;
		int a = 3;
		int b = 0;
		int c = 2;
		for (; n > 2;) {
			int d = a + b;
			sum = sum + d;
			a = b;
			b = c;
			c = d;
			n--;
		}
		return sum;
	}
}
