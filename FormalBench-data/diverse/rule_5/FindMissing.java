
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMissing {

	public static int findMissing(int[] ar, int n) {
		int missing = 1;
		if (n == 0 || n == 1) {
			return missing;
		}
		for (int i = 1; i <= n; i++) {
			if (ar[i - 1] == i) {
				missing = i;
			} else {
				if (ar[i - 1] != i) {
					return i;
				}
			}
		}
		return missing;
	}
}
