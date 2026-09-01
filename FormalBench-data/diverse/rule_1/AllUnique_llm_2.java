
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AllUnique {

	public static Boolean allUnique(int[] args) {
		Set<Integer> test = new HashSet<Integer>();
		for (int index : args) {
			if (test.contains(index)) {
				return false;
			}
			test.add(index);
		}
		return true;
	}
}
