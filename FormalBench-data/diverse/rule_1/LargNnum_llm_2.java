
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargNnum {

	public static int[] largNnum(int[] arg, int Array) {
		int[] ret = Arrays.copyOf(arg, arg.length);

		Arrays.sort(ret);
		for (int index = 0; index < ret.length / 2; index++) {
			int id = ret[index];
			ret[index] = ret[ret.length - 1
					- index];
			ret[ret.length - 1 - index] = id;
		}

		int[] result = Arrays.copyOf(ret, Array);
		return result;
	}
}
