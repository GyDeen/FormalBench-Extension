import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDivisors {

	public static String countDivisors(int n) {
		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (0 == n % i) {
				count++;
			}
		}
		if (0 == count % 2) {
			return "Even";
		} else {
			return "Odd";
		}
	}
}
