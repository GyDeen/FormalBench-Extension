
import java.io.*;
import java.lang.*;

class Frequency {

	public static int frequency(int[] a, int x) {
		int count = 0;
		int start = 0;
		while (start < a.length) {
			if (a[start] == x) {
				count++;
			}
			start++;
		}
		return count;
	}
}
