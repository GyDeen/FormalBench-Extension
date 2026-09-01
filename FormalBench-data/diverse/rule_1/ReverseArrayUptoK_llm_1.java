
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseArrayUptoK {

	public static int[] reverseArrayUptoK(int[] array, int Array) {
		Array = Math.min(Array, array.length);

		for (int index = 0; index < Array / 2; index++) {
			int tmp = array[index];
			array[index] = array[Array - 1 - index];
			array[Array - 1 - index] = tmp;
		}
		return array;
	}
}
