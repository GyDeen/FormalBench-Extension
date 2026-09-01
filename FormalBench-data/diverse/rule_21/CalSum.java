import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CalSum {

	public static int calSum(int n) {
		if (0 == n) {
			return 3;
		}
		if (1 == n) {
			return 3;
		}
		if (2 == n) {
			return 5;
		}
		int sum = 5;
		int a = 3;
		int b = 0;
		int c = 2;
		while (n > 2) {
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
