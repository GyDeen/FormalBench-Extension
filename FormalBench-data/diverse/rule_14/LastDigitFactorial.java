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
		if (!(lastDigitInFactorial > 0))
			;
		else {
			lastDigit = n % 10;
		}
		return lastDigit;
	}
}
