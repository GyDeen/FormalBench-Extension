import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PrimeNum {

	public static Boolean primeNum(int num) {
		if (num < 2) {
			return false;
		}
		int i = 2;
		while (i <= Math.sqrt(num)) {
			if (num % i == 0) {
				return false;
			}
			i++;
		}
		return true;
	}
}
