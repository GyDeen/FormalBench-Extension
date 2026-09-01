
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindFirstMissing {

	public static int findFirstMissing(int[] args, int start, int stop) {
		int found = start;
		int num = stop;
		int mid;

		while (found <= num) {
			mid = (found + num) / 2;
			int i = args[mid];

			if (i == mid) {
				found = mid + 1;
			} else if (i < mid) {
				found = mid + 1;
			} else {
				num = mid - 1;
			}
		}
		return found;
	}
}
