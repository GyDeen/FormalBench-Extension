import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDivisors {

	public static String countDivisors(int n) {
		int count = 0;
		int total = 1;
		while (total <= n) {
			if (n % total == 0) {
				count++;
			}
			total++;
		}
		if (count % 2 == 0) {
			return "Even";
		} else {
			return "Odd";
		}
	}
}
