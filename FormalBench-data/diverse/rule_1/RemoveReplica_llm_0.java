
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveReplica {

	public static Object[] removeReplica(int[] array) {
		int num = array.length;
		Object[] ret = new Object[num];
		Set<Integer> sets = new HashSet<>();

		for (int test = 0; test < num; test++) {
			if (!sets.contains(array[test])) {
				ret[test] = array[test];
				sets.add(array[test]);
			} else {
				ret[test] = "MSP";
			}
		}

		return ret;
	}
}
