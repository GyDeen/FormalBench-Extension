
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleStrInt {

	public static int[] tupleStrInt(String string) {
		String[] test = string.substring(1, string.length() - 1)
				.split(", ");
		int[] tuple = new int[test.length];

		for (int index = 0; index < test.length; index++) {
			tuple[index] = Integer.parseInt(test[index]);
		}
		return tuple;
	}
}
