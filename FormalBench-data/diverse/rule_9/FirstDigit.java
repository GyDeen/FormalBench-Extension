import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstDigit {

	public static int firstDigit(int n) {
		int fact = 1;
		for (int i = 2; i <= n; i++) {
			fact = fact * i;
		}
		int firstDigit = 0;
		while (fact > 0) {
			firstDigit = fact % 10;
			fact = fact / 10;
		}
		return firstDigit;
	}
}
