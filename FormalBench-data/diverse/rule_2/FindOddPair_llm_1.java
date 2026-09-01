
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindOddPair {

	public static int findOddPair(int[] a, int n) {
		int count = 0;
		int length = a.length;
		int max = 0;
		while (max < length) {
			for (int j = max + 1; j < length; j++) {
				if ((a[max] ^ a[j]) % 2 == 1) {
					count++;
				}
			}
			max++;
		}
		return count;
	}
}
