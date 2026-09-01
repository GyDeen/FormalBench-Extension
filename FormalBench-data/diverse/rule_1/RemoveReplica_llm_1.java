
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveReplica {

	public static Object[] removeReplica(int[] array) {
		int test = array.length;
		Object[] ret = new Object[test];
		Set<Integer> sets = new HashSet<>();

		for (int index = 0; index < test; index++) {
			if (!sets.contains(array[index])) {
				ret[index] = array[index];
				sets.add(array[index]);
			} else {
				ret[index] = "MSP";
			}
		}

		return ret;
	}
}
