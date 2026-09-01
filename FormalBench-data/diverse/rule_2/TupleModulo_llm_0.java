
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleModulo {

	public static int[] tupleModulo(int[] testTup1, int[] testTup2) {
		int length = testTup1.length;
		int[] result = new int[length];
		int num = 0;
		while (num < length) {
			result[num] = testTup1[num] % testTup2[num];
			num++;
		}
		return result;
	}
}
