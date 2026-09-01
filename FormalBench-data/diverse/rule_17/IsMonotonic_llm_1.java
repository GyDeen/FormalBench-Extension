
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsMonotonic {

	public static Boolean isMonotonic(int[] a) {
		int n = a.length;
		boolean inc = true;
		boolean dec = true;
		for (int i = 1; i < n; i++) {
			int j = i - 1;
			if (a[i] < a[j]) {
				inc = false;
			}
			int c = i - 1;
			if (a[i] > a[c]) {
				dec = false;
			}
		}
		return inc || dec;
	}
}
