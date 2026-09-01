import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsAbundant {

	public static Boolean isAbundant(int n) {
		int sum = 0;
		int max = n / 2;
		for (int i = 1; i <= max; i++) {
			int j = n % i;
			if (j == 0) {
				sum += i;
			}
		}
		return sum > n;
	}
}
