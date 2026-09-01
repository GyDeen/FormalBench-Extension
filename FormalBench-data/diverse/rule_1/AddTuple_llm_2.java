
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddTuple {

	public static int[] addTuple(int[] array, int[] add) {
		int[] test = new int[array.length + add.length];
		System.arraycopy(array, 0, test, 0, array.length);
		System.arraycopy(add, 0, test, array.length,
				add.length);
		return test;
	}
}
