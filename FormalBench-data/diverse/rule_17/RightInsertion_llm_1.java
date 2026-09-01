
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RightInsertion {

	public static int rightInsertion(int[] a, int x) {
		int i = 0;
		int j = a.length - 1;
		while (i <= j) {
			int tmp = i + j;
			int mid = (tmp) / 2;
			if (a[mid] == x) {
				return mid;
			} else if (a[mid] < x) {
				i = mid + 1;
			} else {
				j = mid - 1;
			}
		}
		return i;
	}
}
