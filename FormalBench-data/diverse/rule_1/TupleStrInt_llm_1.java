
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleStrInt {

	public static int[] tupleStrInt(String string) {
		String[] test = string.substring(1, string.length() - 1)
				.split(", ");
		int[] result = new int[test.length];

		for (int index = 0; index < test.length; index++) {
			result[index] = Integer.parseInt(test[index]);
		}
		return result;
	}
}
