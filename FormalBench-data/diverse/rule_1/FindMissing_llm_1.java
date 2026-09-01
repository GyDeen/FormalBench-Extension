
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMissing {

	public static int findMissing(int[] args, int array) {
		int find = 1;
		if (array == 0 || array == 1) {
			return find;
		}
		for (int remaining = 1; remaining <= array; remaining++) {
			if (args[remaining - 1] == remaining) {
				find = remaining;
			} else if (args[remaining - 1] != remaining) {
				return remaining;
			}
		}
		return find;
	}
}
