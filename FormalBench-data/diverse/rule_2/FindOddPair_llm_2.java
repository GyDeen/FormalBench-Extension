
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindOddPair {

	public static int findOddPair(int[] a, int n) {
		int count = 0;
		int length = a.length;
		int total = 0;
		while (total < length) {
			for (int j = total + 1; j < length; j++) {
				if ((a[total] ^ a[j]) % 2 == 1) {
					count++;
				}
			}
			total++;
		}
		return count;
	}
}
