
import java.io.*;
import java.lang.*;

class Frequency {

	public static int frequency(int[] a, int x) {
		int count = 0;
		int offset = 0;
		while (offset < a.length) {
			if (a[offset] == x) {
				count++;
			}
			offset++;
		}
		return count;
	}
}
