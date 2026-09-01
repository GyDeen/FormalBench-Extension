
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTuples {

	public static Boolean checkTuples(int[] tu, int[] test) {
		HashSet<Integer> result = new HashSet<>();
		for (int index : test) {
			result.add(index);
		}
		for (int index : tu) {
			if (!result.contains(index)) {
				return false;
			}
		}
		return true;
	}
}
