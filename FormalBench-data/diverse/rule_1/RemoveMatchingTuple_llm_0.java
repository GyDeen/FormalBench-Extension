
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveMatchingTuple {

	public static String[][] removeMatchingTuple(String[][] testArray1, String[][] test) {
		List<String[]> list = new ArrayList<>();

		outerLoop: for (String[] match : testArray1) {
			for (String[] tuple : test) {
				if (Arrays.equals(match, tuple)) {
					continue outerLoop;
				}
			}
			list.add(match);
		}

		String[][] remove = new String[list.size()][];
		return list.toArray(remove);
	}
}
