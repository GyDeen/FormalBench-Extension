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
				int b = i % factors[a];
				if (b != 0) {
					i += n;
					break;
				}
				boolean result = factors[a] == factors[n - 1];
				boolean is = i % factors[a] == 0;
				if (result && is) {
					return i;
				}
			}
		}
	}
}
