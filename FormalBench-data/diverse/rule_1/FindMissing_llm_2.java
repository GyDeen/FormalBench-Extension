
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMissing {

	public static int findMissing(int[] args, int array) {
		int found = 1;
		if (array == 0 || array == 1) {
			return found;
		}
		for (int num = 1; num <= array; num++) {
			if (args[num - 1] == num) {
				found = num;
			} else if (args[num - 1] != num) {
				return num;
			}
		}
		return found;
	}
}
