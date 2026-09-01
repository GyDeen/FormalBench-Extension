
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NthItems {

	public static int[] nthItems(int[] args, int n) {
		int ret = (args.length + n - 1) / n;
		int[] items = new int[ret];

		for (int count = 0, ii = 0; count < args.length; count += n, ii++) {
			items[ii] = args[count];
		}
		return items;
	}
}
