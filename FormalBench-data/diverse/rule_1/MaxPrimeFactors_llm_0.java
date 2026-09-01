import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxPrimeFactors {

	public static int maxPrimeFactors(int num) {
		int result = 0;
		for (int j = 2; j <= num; j++) {
			while (num % j == 0) {
				result = j;
				num /= j;
			}
		}
		return result;
	}
}
