import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsAbundant {

	public static Boolean isAbundant(int n) {
		int sum = 0;
		for (int i = 1; n / 2 >= i; i++) {
			if (n % i == 0) {
				sum += i;
			}
		}
		return n < sum;
	}
}
