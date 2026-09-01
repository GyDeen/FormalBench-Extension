import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LastDigitFactorial {

	public static int lastDigitFactorial(int n) {
		int lastDigit = 0;
		int factorial = 1;
		for (int i = n; i >= 1; i--) {
			factorial = factorial * i;
		}
		int lastDigitInFactorial = (int) Math.log10(factorial);
		lastDigit = (lastDigitInFactorial > 0) ? n % 10 : lastDigit;
		return lastDigit;
	}
}
