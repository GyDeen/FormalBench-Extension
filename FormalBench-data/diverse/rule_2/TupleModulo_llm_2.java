
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleModulo {

	public static int[] tupleModulo(int[] testTup1, int[] testTup2) {
		int length = testTup1.length;
		int[] result = new int[length];
		int tmp = 0;
		while (tmp < length) {
			result[tmp] = testTup1[tmp] % testTup2[tmp];
			tmp++;
		}
		return result;
	}
}
