import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDivisors {

	public static String countDivisors(int n) {
		int count = 0;
		for (int i = 1; i <= n; i++) {
			int tmp = n % i;
			if (tmp == 0) {
				count++;
			}
		}
		int end = count % 2;
		if (end == 0) {
			return "Even";
		} else {
			return "Odd";
		}
	}
}
