
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
		int found = 1;
		while (found <= n) {
			if (ar[found - 1] == found) {
				missing = found;
			} else if (ar[found - 1] != found) {
				return found;
			}
			found++;
		}
		return missing;
	}
}
