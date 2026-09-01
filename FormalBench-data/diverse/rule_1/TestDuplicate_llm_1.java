
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TestDuplicate {

	public static Boolean testDuplicate(int[] arr) {
		Set<Integer> elements = new HashSet<Integer>();
		for (int array : arr) {
			if (elements.contains(array)) {
				return true;
			} else {
				elements.add(array);
			}
		}
		return false;
	}
}
