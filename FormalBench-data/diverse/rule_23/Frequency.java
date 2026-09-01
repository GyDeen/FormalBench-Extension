
import java.io.*;
import java.lang.*;

class Frequency {

	public static int frequency(int[] a, int x) {
		int count = 0;
		for (int i = 0; a.length > i; i++) {
			if (a[i] == x) {
				count++;
			}
		}
		return count;
	}
}
