
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateNested {

	public static int[] concatenateNested(int[] array, int[] Test) {
		int[] test = new int[array.length + Test.length];

		System.arraycopy(array, 0, test, 0, array.length);

		System.arraycopy(Test, 0, test, array.length,
				Test.length);

		return test;
	}
}
