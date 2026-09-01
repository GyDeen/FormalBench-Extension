
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
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				int x = a[i];
				int y = a[j];
				int v = x ^ y;
				if ((v & 1) == 0) {
					evenPairCount += 1;
				}
			}
		}
		return evenPairCount;
	}
}
