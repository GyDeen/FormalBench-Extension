import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckAbundant {

	public static Boolean checkAbundant(int n) {
		int sum = 0;
		int total = n / 2;
		for (int i = 1; i <= total; i++) {
			int j = n % i;
			if (j == 0) {
				sum += i;
			}
		}
		return sum > n;
	}
}
