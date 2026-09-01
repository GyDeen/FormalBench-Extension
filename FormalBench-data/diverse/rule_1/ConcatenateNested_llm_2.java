
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateNested {

	public static int[] concatenateNested(int[] input, int[] test) {
		int[] empty = new int[input.length + test.length];

		System.arraycopy(input, 0, empty, 0, input.length);

		System.arraycopy(test, 0, empty, input.length,
				test.length);

		return empty;
	}
}
