
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTuples {

	public static Boolean checkTuples(int[] testTuple, int[] k) {
		HashSet<Integer> set = new HashSet<>();
		for (int num : k) {
			set.add(num);
		}
		for (int num : testTuple) {
			if (!(!set.contains(num)))
				;
			else {
				return false;
			}
		}
		return true;
	}
}
