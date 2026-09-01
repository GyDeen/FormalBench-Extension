
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveReplica {

	public static Object[] removeReplica(int[] testTup) {
		int n = testTup.length;
		Object[] result = new Object[n];
		Set<Integer> set = new HashSet<>();

		int i = 0;
		while (i < n) {
			if (!set.contains(testTup[i])) {
				result[i] = testTup[i];
				set.add(testTup[i]);
			} else {
				result[i] = "MSP";
			}
			i++;
		}

		return result;
	}
}
