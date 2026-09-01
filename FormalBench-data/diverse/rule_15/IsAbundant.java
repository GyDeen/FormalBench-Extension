import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsAbundant {

	public static Boolean isAbundant(int n) {
		int sum = 0;
		for (int i = 1; i <= n / 2; i++) {
			sum = (n % i == 0) ? i : sum;
		}
		return sum > n;
	}
}
