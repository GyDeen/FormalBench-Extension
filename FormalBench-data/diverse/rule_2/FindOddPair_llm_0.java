
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindOddPair {

	public static int findOddPair(int[] a, int n) {
		int count = 0;
		int length = a.length;
		int offset = 0;
		while (offset < length) {
			for (int j = offset + 1; j < length; j++) {
				if ((a[offset] ^ a[j]) % 2 == 1) {
					count++;
				}
			}
			offset++;
		}
		return count;
	}
}
