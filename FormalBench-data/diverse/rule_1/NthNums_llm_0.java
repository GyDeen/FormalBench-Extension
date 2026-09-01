
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NthNums {

	public static int[] nthNums(int[] array, int Array) {
		int[] ret = new int[array.length];
		for (int num = 0; num < array.length; num++) {
			ret[num] = (int) (Math.pow(array[num], Array));
		}
		return ret;
	}
}
