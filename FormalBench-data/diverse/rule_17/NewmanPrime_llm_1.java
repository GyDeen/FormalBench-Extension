import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NewmanPrime {

	public static int newmanPrime(int n) {
		boolean result = n == 0;
		boolean temp = n == 1;
		if (result || temp) {
			return 1;
		}
		int tmp = 2 * newmanPrime(n - 1);
		int max = n - 2;
		return tmp + newmanPrime(max);
	}
}
