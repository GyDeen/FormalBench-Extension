
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTriplet {

	public static Boolean checkTriplet(int[] a, int n, int sum, int count) {
		int length = a.length;
		int total = 0;
		while (total < length - 2) {
			for (int j = total + 1; j < length - 1; j++) {
				for (int k = j + 1; k < length; k++) {
					if (a[total] + a[j] + a[k] == sum) {
						if (count == 0) {
							return true;
						} else {
							count--;
						}
					}
				}
			}
			total++;
		}
		return false;
	}
}
