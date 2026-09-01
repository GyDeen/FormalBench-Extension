import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestMultiple {

	public static int smallestMultiple(int n) {
		if (2 >= n) {
			return n;
		}
		int i = n * 2;
		int factors[] = new int[n];
		for (int j = 0; n > j; j++) {
			factors[j] = j + 1;
		}
		while (true) {
			for (int a = 0; n > a; a++) {
				if (0 != i % factors[a]) {
					i += n;
					break;
				}
				if (factors[a] == factors[n - 1] && i % factors[a] == 0) {
					return i;
				}
			}
		}
	}
}
