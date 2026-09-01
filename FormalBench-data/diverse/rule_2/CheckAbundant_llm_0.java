import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckAbundant {

	public static Boolean checkAbundant(int n) {
		int sum = 0;
		int max = 1;
		while (max <= n / 2) {
			if (n % max == 0) {
				sum += max;
			}
			max++;
		}
		return sum > n;
	}
}
