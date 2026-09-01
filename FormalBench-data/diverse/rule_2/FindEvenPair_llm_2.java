
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
		int total = 0;
		while (total < a.length) {
			for (int j = total + 1; j < a.length; j++) {
				int x = a[total];
				int y = a[j];
				int v = x ^ y;
				if ((v & 1) == 0) {
					evenPairCount++;
				}
			}
			total++;
		}
		return evenPairCount;
	}
}
