
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
		for (int i = 0; a.length > i; i++) {
			for (int j = i + 1; a.length > j; j++) {
				int x = a[i];
				int y = a[j];
				int v = x ^ y;
				if ((v & 1) == 0) {
					evenPairCount++;
				}
			}
		}
		return evenPairCount;
	}
}
