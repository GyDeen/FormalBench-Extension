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
		int sum = 5;
		if (n == 2) {
			return 5;
		}
		int b = 0;
		int a = 3;
		int c = 2;
		while (n > 2) {
			int d = a + b;
			a = b;
			sum = sum + d;
			b = c;
			n--;
			c = d;
		}
		return sum;
	}
}
