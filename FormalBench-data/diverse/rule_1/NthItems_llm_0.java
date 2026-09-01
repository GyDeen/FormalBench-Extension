
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NthItems {

	public static int[] nthItems(int[] array, int n) {
		int ret = (array.length + n - 1) / n;
		int[] items = new int[ret];

		for (int count = 0, ii = 0; count < array.length; count += n, ii++) {
			items[ii] = array[count];
		}
		return items;
	}
}
