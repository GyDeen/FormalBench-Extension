import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestMultiple {

	public static int smallestMultiple(int n) {
		if (n <= 2) {
			return n;
		}
		int i = n * 2;
		int factors[] = new int[n];
		for (int j = 0; j < n; j++) {
			factors[j] = j + 1;
		}
		while (true) {
			for (int a = 0; a < n; a++) {
				if (i % factors[a] != 0) {
					i += n;
					break;
				}
				if (factors[a] == factors[n - 1]) {
					if (i % factors[a] == 0) {
						return i;
					}
				}
			}
		}
	}
}
