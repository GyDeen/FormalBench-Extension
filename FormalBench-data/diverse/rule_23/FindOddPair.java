
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindOddPair {

	public static int findOddPair(int[] a, int n) {
		int count = 0;
		int length = a.length;
		for (int i = 0; length > i; i++) {
			for (int j = i + 1; length > j; j++) {
				if ((a[i] ^ a[j]) % 2 == 1) {
					count++;
				}
			}
		}
		return count;
	}
}
