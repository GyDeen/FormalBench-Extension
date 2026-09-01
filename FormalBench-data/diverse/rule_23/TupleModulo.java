
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleModulo {

	public static int[] tupleModulo(int[] testTup1, int[] testTup2) {
		int length = testTup1.length;
		int[] result = new int[length];
		for (int i = 0; length > i; i++) {
			result[i] = testTup1[i] % testTup2[i];
		}
		return result;
	}
}
