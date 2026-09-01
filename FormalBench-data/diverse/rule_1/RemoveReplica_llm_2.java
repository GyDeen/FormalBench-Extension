
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveReplica {

	public static Object[] removeReplica(int[] rows) {
		int num = rows.length;
		Object[] ret = new Object[num];
		Set<Integer> sets = new HashSet<>();

		for (int test = 0; test < num; test++) {
			if (!sets.contains(rows[test])) {
				ret[test] = rows[test];
				sets.add(rows[test]);
			} else {
				ret[test] = "MSP";
			}
		}

		return ret;
	}
}
