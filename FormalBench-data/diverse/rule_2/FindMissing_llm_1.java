
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
		int remaining = 1;
		while (remaining <= n) {
			if (ar[remaining - 1] == remaining) {
				missing = remaining;
			} else if (ar[remaining - 1] != remaining) {
				return remaining;
			}
			remaining++;
		}
		return missing;
	}
}
