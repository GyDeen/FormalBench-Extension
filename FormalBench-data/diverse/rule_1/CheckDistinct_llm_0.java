
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckDistinct {

	public static Boolean checkDistinct(int[] args) {
		Set<Integer> test = new HashSet<Integer>();
		for (int index = 0; index < args.length; index++) {
			if (test.contains(args[index])) {
				return false;
			} else {
				test.add(args[index]);
			}
		}
		return true;
	}
}
