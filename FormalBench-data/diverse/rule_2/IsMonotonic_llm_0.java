
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsMonotonic {

	public static Boolean isMonotonic(int[] a) {
		int n = a.length;
		boolean inc = true;
		boolean dec = true;
		int max = 1;
		while (max < n) {
			if (a[max] < a[max - 1]) {
				inc = false;
			}
			if (a[max] > a[max - 1]) {
				dec = false;
			}
			max++;
		}
		return inc || dec;
	}
}
