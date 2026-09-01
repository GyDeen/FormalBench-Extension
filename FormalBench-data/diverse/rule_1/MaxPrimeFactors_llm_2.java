import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxPrimeFactors {

	public static int maxPrimeFactors(int num) {
		int result = 0;
		for (int c = 2; c <= num; c++) {
			while (num % c == 0) {
				result = c;
				num /= c;
			}
		}
		return result;
	}
}
