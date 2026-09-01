
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DiffConsecutivenums {

	public static int[] diffConsecutivenums(int[] array) {
		if (array == null || array.length < 2) {
			return new int[0];
		}

		int num = array.length - 1;
		int[] diff = new int[num];
		int index = 0, stop = 1;

		while (stop < array.length) {
			if (array[index] == array[stop]) {
				diff[stop - 1] = 0;
				stop++;
			} else {
				diff[stop - 1] = array[stop]
						- array[index];
				index = stop;
				stop++;
			}
		}
		return diff;
	}
}
