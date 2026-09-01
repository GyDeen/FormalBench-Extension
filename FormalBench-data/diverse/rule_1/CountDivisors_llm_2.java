import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDivisors {

	public static String countDivisors(int number) {
		int result = 0;
		for (int c = 1; c <= number; c++) {
			if (number % c == 0) {
				result++;
			}
		}
		if (result % 2 == 0) {
			return "Even";
		} else {
			return "Odd";
		}
	}
}
