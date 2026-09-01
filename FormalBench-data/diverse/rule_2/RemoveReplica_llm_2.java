
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveReplica {

	public static Object[] removeReplica(int[] testTup) {
		int n = testTup.length;
		Object[] result = new Object[n];
		Set<Integer> set = new HashSet<>();

		int id = 0;
		while (id < n) {
			if (!set.contains(testTup[id])) {
				result[id] = testTup[id];
				set.add(testTup[id]);
			} else {
				result[id] = "MSP";
			}
			id++;
		}

		return result;
	}
}
