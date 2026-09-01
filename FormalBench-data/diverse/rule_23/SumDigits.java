import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDigits {

	public static int sumDigits(int n) {
		int sum = 0;
		while (0 < n) {
			sum += n % 10;
			n /= 10;
		}
		return sum;
	}
}
