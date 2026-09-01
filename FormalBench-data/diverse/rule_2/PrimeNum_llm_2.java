import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PrimeNum {

	public static Boolean primeNum(int num) {
		if (num < 2) {
			return false;
		}
		int n = 2;
		while (n <= Math.sqrt(num)) {
			if (num % n == 0) {
				return false;
			}
			n++;
		}
		return true;
	}
}
