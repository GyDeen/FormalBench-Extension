import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDivisors {

	public static String countDivisors(int n) {
		int count = 0;
		for (int i = 1; n >= i; i++) {
			if (n % i == 0) {
				count++;
			}
		}
		if (count % 2 == 0) {
			return "Even";
		} else {
			return "Odd";
		}
	}
}
