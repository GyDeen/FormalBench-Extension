import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LastDigitFactorial {

	public static int lastDigitFactorial(int number) {
		int result = 0;
		int N = 1;
		for (int num = number; num >= 1; num--) {
			N = N * num;
		}
		int last = (int) Math.log10(N);
		if (last > 0) {
			result = number % 10;
		}
		return result;
	}
}
