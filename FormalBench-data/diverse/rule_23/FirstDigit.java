import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstDigit {

	public static int firstDigit(int n) {
		int fact = 1;
		for (int i = 2; n >= i; i++) {
			fact *= i;
		}
		int firstDigit = 0;
		while (0 < fact) {
			firstDigit = fact % 10;
			fact /= 10;
		}
		return firstDigit;
	}
}
