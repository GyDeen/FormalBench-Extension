
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AllUnique {

	public static Boolean allUnique(int[] arr) {
		Set<Integer> sets = new HashSet<Integer>();
		for (int test : arr) {
			if (sets.contains(test)) {
				return false;
			}
			sets.add(test);
		}
		return true;
	}
}
