
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Overlapping {

	public static Boolean overlapping(int[] array1, int[] array2) {
		int i = 0;
		int j = 0;

		while (i < array1.length && j < array2.length) {
			if (array2[j] == array1[i]) {
				j++;
				if (array2.length == j) {
					return true;
				}
			} else {
				j = 0;
			}
			i++;
		}
		return false;
	}
}
