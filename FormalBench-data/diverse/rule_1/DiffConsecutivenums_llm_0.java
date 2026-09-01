
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DiffConsecutivenums {

	public static int[] diffConsecutivenums(int[] diff) {
		if (diff == null || diff.length < 2) {
			return new int[0];
		}

		int num = diff.length - 1;
		int[] difference = new int[num];
		int index = 0, stop = 1;

		while (stop < diff.length) {
			if (diff[index] == diff[stop]) {
				difference[stop - 1] = 0;
				stop++;
			} else {
				difference[stop - 1] = diff[stop]
						- diff[index];
				index = stop;
				stop++;
			}
		}
		return difference;
	}
}
