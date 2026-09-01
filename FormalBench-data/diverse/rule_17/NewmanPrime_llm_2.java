import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NewmanPrime {

	public static int newmanPrime(int n) {
		boolean b = n == 0;
		boolean tmp = n == 1;
		if (b || tmp) {
			return 1;
		}
		int result = 2 * newmanPrime(n - 1);
		int max = n - 2;
		return result + newmanPrime(max);
	}
}
