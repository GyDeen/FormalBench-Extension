import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PrimeNum {

	public static Boolean primeNum(int seed) {
		if (seed < 2) {
			return false;
		}
		for (int n = 2; n <= Math.sqrt(seed); n++) {
			if (seed % n == 0) {
				return false;
			}
		}
		return true;
	}
}
