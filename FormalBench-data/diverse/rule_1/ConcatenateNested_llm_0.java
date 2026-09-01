
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateNested {

	public static int[] concatenateNested(int[] array, int[] test) {
		int[] empty = new int[array.length + test.length];

		System.arraycopy(array, 0, empty, 0, array.length);

		System.arraycopy(test, 0, empty, array.length,
				test.length);

		return empty;
	}
}
