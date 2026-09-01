
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveReplica {

	public static Object[] removeReplica(int[] testTup) {
		int n = testTup.length;
		Object[] result = new Object[n];
		Set<Integer> set = new HashSet<>();

		int index = 0;
		while (index < n) {
			if (!set.contains(testTup[index])) {
				result[index] = testTup[index];
				set.add(testTup[index]);
			} else {
				result[index] = "MSP";
			}
			index++;
		}

		return result;
	}
}
