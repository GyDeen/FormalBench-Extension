
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindExponentio {

	public static int[] findExponentio(int[] array, int[] test) {
		int[] find = new int[array.length];
		for (int index = 0; index < array.length; index++) {
			find[index] = (int) Math.pow(array[index],
					test[index]);
		}
		return find;
	}
}
