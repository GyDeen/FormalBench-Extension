
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleStrInt {

	public static int[] tupleStrInt(String testStr) {
		String[] tupleParts = testStr.substring(1, testStr.length() - 1).split(", ");
		int[] tupleArray = new int[tupleParts.length];

		int array = 0;
		while (array < tupleParts.length) {
			tupleArray[array] = Integer.parseInt(tupleParts[array]);
			array++;
		}
		return tupleArray;
	}
}
