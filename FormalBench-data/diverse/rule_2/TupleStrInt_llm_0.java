
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleStrInt {

	public static int[] tupleStrInt(String testStr) {
		String[] tupleParts = testStr.substring(1, testStr.length() - 1).split(", ");
		int[] tupleArray = new int[tupleParts.length];

		int index = 0;
		while (index < tupleParts.length) {
			tupleArray[index] = Integer.parseInt(tupleParts[index]);
			index++;
		}
		return tupleArray;
	}
}
