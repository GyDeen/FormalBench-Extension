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
		for (int num = 3; num * num <= number; num += 2) {
			if (number % num == 0) {
				return true;
			}
		}
		return false;
	}
}
