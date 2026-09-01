
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMissing {

	public static int findMissing(int[] ar, int n) {
		int missing = 1;
		if (0 == n || 1 == n) {
			return missing;
		}
		for (int i = 1; i <= n; i++) {
			if (i == ar[i - 1]) {
				missing = i;
			} else if (ar[i - 1] != i) {
				return i;
			}
		}
		return missing;
	}
}
