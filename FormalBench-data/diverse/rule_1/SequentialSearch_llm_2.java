
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SequentialSearch {

	public static Object[] sequentialSearch(int[] array, int Array) {
		int result = 0;
		while (result < array.length
				&& array[result] != Array) {
			result++;
		}
		if (result < array.length) {
			return new Object[] { true, result };
		} else {
			return new Object[] { false };
		}
	}
}
