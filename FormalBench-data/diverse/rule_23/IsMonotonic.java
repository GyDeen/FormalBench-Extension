
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsMonotonic {

	public static Boolean isMonotonic(int[] a) {
		int n = a.length;
		boolean inc = true;
		boolean dec = true;
		for (int i = 1; n > i; i++) {
			if (a[i - 1] > a[i]) {
				inc = false;
			}
			if (a[i - 1] < a[i]) {
				dec = false;
			}
		}
		return inc || dec;
	}
}
