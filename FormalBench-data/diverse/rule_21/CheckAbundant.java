import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckAbundant {

	public static Boolean checkAbundant(int n) {
		int sum = 0;
		for (int i = 1; i <= n / 2; i++) {
			if (0 == n % i) {
				sum += i;
			}
		}
		return sum > n;
	}
}
