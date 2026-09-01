
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NthNums {

	public static int[] nthNums(int[] array, int Array) {
		int[] result = new int[array.length];
		for (int num = 0; num < array.length; num++) {
			result[num] = (int) (Math.pow(array[num], Array));
		}
		return result;
	}
}
