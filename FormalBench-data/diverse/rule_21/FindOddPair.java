
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindOddPair {

	public static int findOddPair(int[] a, int n) {
		int count = 0;
		int length = a.length;
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				if (1 == (a[i] ^ a[j]) % 2) {
					count++;
				}
			}
		}
		return count;
	}
}
