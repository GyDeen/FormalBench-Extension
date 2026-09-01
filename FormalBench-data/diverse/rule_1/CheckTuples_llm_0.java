
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTuples {

	public static Boolean checkTuples(int[] tu, int[] test) {
		HashSet<Integer> result = new HashSet<>();
		for (int i : test) {
			result.add(i);
		}
		for (int i : tu) {
			if (!result.contains(i)) {
				return false;
			}
		}
		return true;
	}
}
