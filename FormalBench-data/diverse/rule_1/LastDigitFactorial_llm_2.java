import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LastDigitFactorial {

	public static int lastDigitFactorial(int num) {
		int result = 0;
		int N = 1;
		for (int count = num; count >= 1; count--) {
			N = N * count;
		}
		int last = (int) Math.log10(N);
		if (last > 0) {
			result = num % 10;
		}
		return result;
	}
}
