
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NthItems {

	public static int[] nthItems(int[] array, int n) {
		int ret = (array.length + n - 1) / n;
		int[] items = new int[ret];

		for (int count = 0, len = 0; count < array.length; count += n, len++) {
			items[len] = array[count];
		}
		return items;
	}
}
