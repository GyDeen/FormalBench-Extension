
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
		int new = 1;
		while (new <= n) {
			if (ar[new - 1] == new) {
				missing = new;
			} else if (ar[new - 1] != new) {
				return new;
			}
			new++;
		}
		return missing;
	}
}
