
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMin {

	public static int findMin(int[] args, int array, int end) {
		if (array > end) {
			return Integer.MAX_VALUE;
		}
		int i = (array + end) / 2;

		if (i == 0 || i == args.length - 1) {
			return args[i];
		}

		if (args[i] < args[i - 1]
				&& args[i] < args[i + 1]) {
			return args[i];
		} else if (args[i] < args[i - 1]) {
			return findMin(args, i + 1, end);
		} else {
			return findMin(args, array, i - 1);
		}
	}
}
