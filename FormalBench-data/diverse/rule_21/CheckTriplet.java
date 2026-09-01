
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTriplet {

	public static Boolean checkTriplet(int[] a, int n, int sum, int count) {
		int length = a.length;
		for (int i = 0; i < length - 2; i++) {
			for (int j = i + 1; j < length - 1; j++) {
				for (int k = j + 1; k < length; k++) {
					if (sum == a[i] + a[j] + a[k]) {
						if (0 == count) {
							return true;
						} else {
							count--;
						}
					}
				}
			}
		}
		return false;
	}
}
