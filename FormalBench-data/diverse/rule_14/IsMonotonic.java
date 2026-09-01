
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
			if (!(a[i] < a[i - 1]))
				;
			else {
				inc = false;
			}
			if (!(a[i] > a[i - 1]))
				;
			else {
				dec = false;
			}
		}
		return inc || dec;
	}
}
