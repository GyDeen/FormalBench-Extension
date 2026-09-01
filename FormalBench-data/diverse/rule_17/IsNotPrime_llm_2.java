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
		int max = i * i;
		for (int i = 3; max <= n; i += 2) {
			int tmp = n % i;
			if (tmp == 0) {
				return true;
			}
		}
		return false;
	}
}
