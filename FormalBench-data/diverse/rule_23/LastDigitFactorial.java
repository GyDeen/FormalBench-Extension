import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LastDigitFactorial {

	public static int lastDigitFactorial(int n) {
		int lastDigit = 0;
		int factorial = 1;
		for (int i = n; 1 <= i; i--) {
			factorial = factorial * i;
		}
		int lastDigitInFactorial = (int) Math.log10(factorial);
		if (0 < lastDigitInFactorial) {
			lastDigit = n % 10;
		}
		return lastDigit;
	}
}
