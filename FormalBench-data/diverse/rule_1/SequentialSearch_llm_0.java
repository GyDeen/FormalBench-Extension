
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SequentialSearch {

	public static Object[] sequentialSearch(int[] array, int Array) {
		int i = 0;
		while (i < array.length
				&& array[i] != Array) {
			i++;
		}
		if (i < array.length) {
			return new Object[] { true, i };
		} else {
			return new Object[] { false };
		}
	}
}
