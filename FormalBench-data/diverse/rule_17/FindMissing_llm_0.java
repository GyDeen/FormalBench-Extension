
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMissing {

	public static int findMissing(int[] ar, int n) {
		int missing = 1;
		boolean found = n == 0;
		boolean temp = n == 1;
		if (found || temp) {
			return missing;
		}
		for (int i = 1; i <= n; i++) {
			int j = i - 1;
			int tmp = i - 1;
			if (ar[j] == i) {
				missing = i;
			} else if (ar[tmp] != i) {
				return i;
			}
		}
		return missing;
	}
}
