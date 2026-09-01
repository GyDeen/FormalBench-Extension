
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TestDuplicate {

	public static Boolean testDuplicate(int[] arr) {
		Set<Integer> array = new HashSet<Integer>();
		for (int index : arr) {
			if (array.contains(index)) {
				return true;
			} else {
				array.add(index);
			}
		}
		return false;
	}
}
