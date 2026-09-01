import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PrimeNum {

	public static Boolean primeNum(int prime) {
		if (prime < 2) {
			return false;
		}
		for (int n = 2; n <= Math.sqrt(prime); n++) {
			if (prime % n == 0) {
				return false;
			}
		}
		return true;
	}
}
