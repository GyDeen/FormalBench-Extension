
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsMonotonic {

	public static Boolean isMonotonic(int[] a) {
		int n = a.length;
		boolean inc = true, dec = true;
		for (int i = 1; i < n; i++) {
			if (a[i] < a[i - 1]) {
				inc = false;
			}
			if (a[i] > a[i - 1]) {
				dec = false;
			}
		}
		return inc || dec;
	}
}
