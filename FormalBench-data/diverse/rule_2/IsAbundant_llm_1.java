import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsAbundant {

	public static Boolean isAbundant(int n) {
		int sum = 0;
		int total = 1;
		while (total <= n / 2) {
			if (n % total == 0) {
				sum += total;
			}
			total++;
		}
		return sum > n;
	}
}
