
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestNum {

	public static int smallestNum(int[] args) {
		if (args.length == 0) {
			throw new IllegalArgumentException("Array cannot be empty");
		}

		int smallest = args[0];
		for (int max = 1; max < args.length; max++) {
			if (args[max] < smallest) {
				smallest = args[max];
			}
		}
		return smallest;
	}
}
