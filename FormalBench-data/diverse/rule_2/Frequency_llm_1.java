
import java.io.*;
import java.lang.*;

class Frequency {

	public static int frequency(int[] a, int x) {
		int count = 0;
		int max = 0;
		while (max < a.length) {
			if (a[max] == x) {
				count++;
			}
			max++;
		}
		return count;
	}
}
