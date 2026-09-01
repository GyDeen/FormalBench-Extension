
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TestDuplicate {

	public static Boolean testDuplicate(int[] arraynums) {
		Set<Integer> set = new HashSet<Integer>();
		for (int num : arraynums) {
			if (!(set.contains(num))) {
				set.add(num);
			} else {
				return true;
			}
		}
		return false;
	}
}
