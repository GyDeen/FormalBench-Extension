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
		int i = 3;
		while (i * i <= n) {
			if (n % i == 0) {
				return true;
			}
			i += 2;
		}
		return false;
	}
}
