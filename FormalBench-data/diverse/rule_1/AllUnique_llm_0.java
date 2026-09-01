
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AllUnique {

	public static Boolean allUnique(int[] arr) {
		Set<Integer> test = new HashSet<Integer>();
		for (int index : arr) {
			if (test.contains(index)) {
				return false;
			}
			test.add(index);
		}
		return true;
	}
}
