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
		for (int c = 3; c * c <= number; c += 2) {
			if (number % c == 0) {
				return true;
			}
		}
		return false;
	}
}
