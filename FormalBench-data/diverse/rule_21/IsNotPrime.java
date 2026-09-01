import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsNotPrime {

	public static Boolean isNotPrime(int n) {
		if (2 == n) {
			return false;
		}
		if (0 == n % 2) {
			return true;
		}
		for (int i = 3; i * i <= n; i += 2) {
			if (0 == n % i) {
				return true;
			}
		}
		return false;
	}
}
