
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsMonotonic {

	public static Boolean isMonotonic(int[] a) {
		int n = a.length;
		boolean inc = true;
		boolean dec = true;
		int base = 1;
		while (base < n) {
			if (a[base] < a[base - 1]) {
				inc = false;
			}
			if (a[base] > a[base - 1]) {
				dec = false;
			}
			base++;
		}
		return inc || dec;
	}
}
