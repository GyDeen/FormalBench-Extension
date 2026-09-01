
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindExponentio {

	public static int[] findExponentio(int[] array, int[] tests) {
		int[] test = new int[array.length];
		for (int index = 0; index < array.length; index++) {
			test[index] = (int) Math.pow(array[index],
					tests[index]);
		}
		return test;
	}
}
