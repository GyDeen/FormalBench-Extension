
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleStrInt {

	public static int[] tupleStrInt(String testStr) {
		String[] tupleParts = testStr.substring(1, testStr.length() - 1).split(", ");
		int[] tupleArray = new int[tupleParts.length];

		int num = 0;
		while (num < tupleParts.length) {
			tupleArray[num] = Integer.parseInt(tupleParts[num]);
			num++;
		}
		return tupleArray;
	}
}
