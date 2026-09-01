import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsNotPrime {

	public static Boolean isNotPrime(int n) {
		if (n == 2) {
			return false;
		}
		if (n % 2 == 0) {
			return true;
		}
		int p = 3;
		while (p * p <= n) {
			if (n % p == 0) {
				return true;
			}
			p += 2;
		}
		return false;
	}
}
