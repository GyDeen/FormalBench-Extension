
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Overlapping {

	public static Boolean overlapping(int[] array1, int[] array2) {
		int i = 0;
		int j = 0;

		while (i < array1.length && j < array2.length) {
			if (!(array1[i] == array2[j])) {
				j = 0;
			} else {
				j++;
				if (j == array2.length) {
					return true;
				}
			}
			i++;
		}
		return false;
	}
}
