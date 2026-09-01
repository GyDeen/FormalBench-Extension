
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsMonotonic {

	public static Boolean isMonotonic(int[] a) {
		int n = a.length;
		boolean inc = true;
		boolean dec = true;
		int min = 1;
		while (min < n) {
			if (a[min] < a[min - 1]) {
				inc = false;
			}
			if (a[min] > a[min - 1]) {
				dec = false;
			}
			min++;
		}
		return inc || dec;
	}
}
