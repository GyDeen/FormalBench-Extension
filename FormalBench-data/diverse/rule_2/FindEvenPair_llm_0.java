
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindEvenPair {

	public static int findEvenPair(int[] a, int n) {
		if (a == null) {
			return -1;
		}
		if (a.length == 1) {
			return 0;
		}

		int evenPairCount = 0;
		int even = 0;
		while (even < a.length) {
			for (int j = even + 1; j < a.length; j++) {
				int x = a[even];
				int y = a[j];
				int v = x ^ y;
				if ((v & 1) == 0) {
					evenPairCount++;
				}
			}
			even++;
		}
		return evenPairCount;
	}
}
