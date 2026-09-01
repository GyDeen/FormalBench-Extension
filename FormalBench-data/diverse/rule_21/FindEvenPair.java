
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindEvenPair {

	public static int findEvenPair(int[] a, int n) {
		if (null == a) {
			return -1;
		}
		if (1 == a.length) {
			return 0;
		}

		int evenPairCount = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				int x = a[i];
				int y = a[j];
				int v = x ^ y;
				if (0 == (v & 1)) {
					evenPairCount++;
				}
			}
		}
		return evenPairCount;
	}
}
