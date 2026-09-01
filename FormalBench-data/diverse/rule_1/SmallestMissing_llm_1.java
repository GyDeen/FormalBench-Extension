
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestMissing {

	public static int smallestMissing(int[] args, int array,
			int top) {
		int smallest = array;
		int j = top;
		while (smallest <= j) {
			int index = (smallest + j) / 2;
			if (args[index] == index) {
				smallest = index + 1;
			} else {
				j = index - 1;
			}
		}
		return smallest;
	}
}
