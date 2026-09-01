
import java.io.*;
import java.lang.*;
import java.util.*;

class SubList {

	public static int[] subList(int[] sub, int[] num) {
		int Array = Math.min(sub.length, num.length);
		int[] ret = new int[Array];
		for (int index = 0; index < Array; index++) {
			ret[index] = sub[index]
					- num[index];
		}
		return ret;
	}
}
