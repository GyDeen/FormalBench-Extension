
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckDistinct {

	public static Boolean checkDistinct(int[] arr) {
		Set<Integer> test = new HashSet<Integer>();
		for (int index = 0; index < arr.length; index++) {
			if (test.contains(arr[index])) {
				return false;
			} else {
				test.add(arr[index]);
			}
		}
		return true;
	}
}
