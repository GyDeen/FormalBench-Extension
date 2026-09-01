import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LastDigitFactorial {

	public static int lastDigitFactorial(int number) {
		int ret = 0;
		int result = 1;
		for (int num = number; num >= 1; num--) {
			result = result * num;
		}
		int last = (int) Math.log10(result);
		if (last > 0) {
			ret = number % 10;
		}
		return ret;
	}
}
