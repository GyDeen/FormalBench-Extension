
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargNnum {

	public static int[] largNnum(int[] args, int Array) {
		int[] ret = Arrays.copyOf(args, args.length);

		Arrays.sort(ret);
		for (int index = 0; index < ret.length / 2; index++) {
			int ele = ret[index];
			ret[index] = ret[ret.length - 1
					- index];
			ret[ret.length - 1 - index] = ele;
		}

		int[] result = Arrays.copyOf(ret, Array);
		return result;
	}
}
