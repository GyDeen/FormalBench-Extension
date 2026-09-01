
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckDistinct {

	public static Boolean checkDistinct(int[] args) {
		Set<Integer> sets = new HashSet<Integer>();
		for (int test = 0; test < args.length; test++) {
			if (sets.contains(args[test])) {
				return false;
			} else {
				sets.add(args[test]);
			}
		}
		return true;
	}
}
