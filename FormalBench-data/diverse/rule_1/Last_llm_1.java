
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Last {

	public static int last(int[] array, int x, int y) {
		int i = 0;
		int r = y - 1;
		int k = -1;

		while (i <= r) {
			int tmp = (i + r) / 2;
			if (array[tmp] == x) {
				k = tmp;
				i = tmp + 1;
			} else if (array[tmp] < x) {
				i = tmp + 1;
			} else {
				r = tmp - 1;
			}
		}

		return k;
	}
}
