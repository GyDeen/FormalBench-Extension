import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PrimeNum {

	public static Boolean primeNum(int num) {
		if (num < 2) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (0 == num % i) {
				return false;
			}
		}
		return true;
	}
}
