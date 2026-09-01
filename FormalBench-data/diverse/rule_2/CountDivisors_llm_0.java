import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDivisors {

	public static String countDivisors(int n) {
		int count = 0;
		int result = 1;
		while (result <= n) {
			if (n % result == 0) {
				count++;
			}
			result++;
		}
		if (count % 2 == 0) {
			return "Even";
		} else {
			return "Odd";
		}
	}
}
