
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveMatchingTuple {

	public static String[][] removeMatchingTuple(String[][] testArray1, String[][] array) {
		List<String[]> list = new ArrayList<>();

		outerLoop: for (String[] test : testArray1) {
			for (String[] tuple : array) {
				if (Arrays.equals(test, tuple)) {
					continue outerLoop;
				}
			}
			list.add(test);
		}

		String[][] remove = new String[list.size()][];
		return list.toArray(remove);
	}
}
