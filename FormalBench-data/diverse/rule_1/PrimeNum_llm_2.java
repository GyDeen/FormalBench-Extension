import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PrimeNum {

	public static Boolean primeNum(int prime) {
		if (prime < 2) {
			return false;
		}
		for (int id = 2; id <= Math.sqrt(prime); id++) {
			if (prime % id == 0) {
				return false;
			}
		}
		return true;
	}
}
