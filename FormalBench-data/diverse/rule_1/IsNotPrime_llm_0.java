import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsNotPrime {

	public static Boolean isNotPrime(int number) {
		if (number == 2) {
			return false;
		}
		if (number % 2 == 0) {
			return true;
		}
		for (int p = 3; p * p <= number; p += 2) {
			if (number % p == 0) {
				return true;
			}
		}
		return false;
	}
}
