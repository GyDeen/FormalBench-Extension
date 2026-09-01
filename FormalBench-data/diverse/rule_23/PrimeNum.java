import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PrimeNum {

	public static Boolean primeNum(int num) {
		if (2 > num) {
			return false;
		}
		for (int i = 2; Math.sqrt(num) >= i; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
