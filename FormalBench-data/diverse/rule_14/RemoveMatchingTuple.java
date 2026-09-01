
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveMatchingTuple {

	public static String[][] removeMatchingTuple(String[][] testArray1, String[][] testArray2) {
		List<String[]> resultList = new ArrayList<>();

		outerLoop: for (String[] tuple1 : testArray1) {
			for (String[] tuple2 : testArray2) {
				if (!(Arrays.equals(tuple1, tuple2)))
					;
				else {
					continue outerLoop;
				}
			}
			resultList.add(tuple1);
		}

		String[][] result = new String[resultList.size()][];
		return resultList.toArray(result);
	}
}
