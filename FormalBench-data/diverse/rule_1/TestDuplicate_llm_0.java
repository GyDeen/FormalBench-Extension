
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TestDuplicate {

	public static Boolean testDuplicate(int[] arr) {
		Set<Integer> array = new HashSet<Integer>();
		for (int Array : arr) {
			if (array.contains(Array)) {
				return true;
			} else {
				array.add(Array);
			}
		}
		return false;
	}
}
