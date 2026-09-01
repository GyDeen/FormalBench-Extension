
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NtimesArray {

	public static int[] ntimesList(int[] array, int Array) {
		int[] num = new int[array.length];
		for (int index = 0; index < array.length; index++) {
			num[index] = array[index] * Array;
		}
		return num;
	}
}
