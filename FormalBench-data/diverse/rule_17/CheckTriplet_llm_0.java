
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTriplet {

	public static Boolean checkTriplet(int[] a, int n, int sum, int count) {
		int length = a.length;
		int num = length - 2;
		for (int i = 0; i < num; i++) {
			int tmp = length - 1;
			for (int j = i + 1; j < tmp; j++) {
				for (int k = j + 1; k < length; k++) {
					int c = a[i] + a[j] + a[k];
					if (c == sum) {
						if (count == 0) {
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
