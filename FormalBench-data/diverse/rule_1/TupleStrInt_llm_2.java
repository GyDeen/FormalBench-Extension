
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleStrInt {

	public static int[] tupleStrInt(String string) {
		String[] test = string.substring(1, string.length() - 1)
				.split(", ");
		int[] tuple = new int[test.length];

		for (int array = 0; array < test.length; array++) {
			tuple[array] = Integer.parseInt(test[array]);
		}
		return tuple;
	}
}
