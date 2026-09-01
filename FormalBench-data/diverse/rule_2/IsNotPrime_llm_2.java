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
		int start = 3;
		while (start * start <= n) {
			if (n % start == 0) {
				return true;
			}
			start += 2;
		}
		return false;
	}
}
