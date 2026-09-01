import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LastDigitFactorial {

	public static int lastDigitFactorial(int n) {
		int lastDigit = 0;
		int factorial = 1;
		int last = n;
		while (last >= 1) {
			factorial = factorial * last;
			last--;
		}
		int lastDigitInFactorial = (int) Math.log10(factorial);
		if (lastDigitInFactorial > 0) {
			lastDigit = n % 10;
		}
		return lastDigit;
	}
}
