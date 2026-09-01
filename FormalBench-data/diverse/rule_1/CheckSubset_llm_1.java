
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckSubset {

	public static Boolean checkSubset(int[] args, int[] test) {
		Set<Integer> sets = new HashSet<>();
		for (int index = 0; index < args.length; index++) {
			sets.add(args[index]);
		}
		for (int index = 0; index < test.length; index++) {
			if (!sets.contains(test[index])) {
				return false;
			}
		}
		return true;
	}
}
