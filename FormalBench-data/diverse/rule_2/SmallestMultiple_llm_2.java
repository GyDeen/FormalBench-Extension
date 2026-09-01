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
		int p = 0;
		while (p < n) {
			factors[p] = p + 1;
			p++;
		}
		while (true) {
			int p = 0;
			while (p < n) {
				if (i % factors[p] != 0) {
					i += n;
					break;
				}
				if (factors[p] == factors[n - 1] && i % factors[p] == 0) {
					return i;
				}
				p++;
			}
		}
	}
}
