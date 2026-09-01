
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddTuple {

	public static int[] addTuple(int[] array, int[] result) {
		int[] test = new int[array.length + result.length];
		System.arraycopy(array, 0, test, 0, array.length);
		System.arraycopy(result, 0, test, array.length,
				result.length);
		return test;
	}
}
