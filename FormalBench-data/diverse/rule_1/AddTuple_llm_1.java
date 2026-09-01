
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddTuple {

	public static int[] addTuple(int[] array, int[] test) {
		int[] add = new int[array.length + test.length];
		System.arraycopy(array, 0, add, 0, array.length);
		System.arraycopy(test, 0, add, array.length,
				test.length);
		return add;
	}
}
