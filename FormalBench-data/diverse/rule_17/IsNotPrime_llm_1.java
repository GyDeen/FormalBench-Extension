import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsNotPrime {

	public static Boolean isNotPrime(int n) {
		if (n == 2) {
			return false;
		}
		int p = n % 2;
		if (p == 0) {
			return true;
		}
		int tmp = i * i;
		for (int i = 3; tmp <= n; i += 2) {
			int r = n % i;
			if (r == 0) {
				return true;
			}
		}
		return false;
	}
}
