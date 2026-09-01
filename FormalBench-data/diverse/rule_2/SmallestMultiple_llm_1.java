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
		int smallest = 0;
		while (smallest < n) {
			factors[smallest] = smallest + 1;
			smallest++;
		}
		while (true) {
			int smallest = 0;
			while (smallest < n) {
				if (i % factors[smallest] != 0) {
					i += n;
					break;
				}
				if (factors[smallest] == factors[n - 1] && i % factors[smallest] == 0) {
					return i;
				}
				smallest++;
			}
		}
	}
}
