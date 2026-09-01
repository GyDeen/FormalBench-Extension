
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseArrayUptoK {

	public static int[] reverseArrayUptoK(int[] array, int Array) {
		Array = Math.min(Array, array.length);

		for (int n = 0; n < Array / 2; n++) {
			int j = array[n];
			array[n] = array[Array - 1 - n];
			array[Array - 1 - n] = j;
		}
		return array;
	}
}
