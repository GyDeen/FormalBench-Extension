
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RotateLeft {

	public static int[] rotateLeft(int[] array, int m, int n) {
		int totalLength = n + (array.length - m);
		int[] rotatedArray = new int[totalLength];

		int index = 0;
		int i = m;
		while (i < array.length) {
			rotatedArray[index++] = array[i];
			i++;
		}

		int i = 0;
		while (i < n) {
			rotatedArray[index++] = array[i];
			i++;
		}

		return rotatedArray;
	}
}
