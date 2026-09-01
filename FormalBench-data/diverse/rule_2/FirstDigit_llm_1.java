import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstDigit {

	public static int firstDigit(int n) {
		int fact = 1;
		int result = 2;
		while (result <= n) {
			fact *= result;
			result++;
		}
		int firstDigit = 0;
		while (fact > 0) {
			firstDigit = fact % 10;
			fact /= 10;
		}
		return firstDigit;
	}
}
