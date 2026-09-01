import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDivisors {

	public static String countDivisors(int n) {
		int count = 0;
		int divide = 1;
		while (divide <= n) {
			if (n % divide == 0) {
				count++;
			}
			divide++;
		}
		if (count % 2 == 0) {
			return "Even";
		} else {
			return "Odd";
		}
	}
}
