
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestMissing {

	public static int smallestMissing(int[] args, int array,
			int right) {
		int smallest = array;
		int j = right;
		while (smallest <= j) {
			int k = (smallest + j) / 2;
			if (args[k] == k) {
				smallest = k + 1;
			} else {
				j = k - 1;
			}
		}
		return smallest;
	}
}
