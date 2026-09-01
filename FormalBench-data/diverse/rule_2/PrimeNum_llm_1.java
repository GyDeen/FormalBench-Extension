import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PrimeNum {

	public static Boolean primeNum(int num) {
		if (num < 2) {
			return false;
		}
		int index = 2;
		while (index <= Math.sqrt(num)) {
			if (num % index == 0) {
				return false;
			}
			index++;
		}
		return true;
	}
}
