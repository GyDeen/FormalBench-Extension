
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NthNums {

	public static int[] nthNums(int[] array, int Array) {
		int[] num = new int[array.length];
		for (int index = 0; index < array.length; index++) {
			num[index] = (int) (Math.pow(array[index], Array));
		}
		return num;
	}
}
