
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RotateLeft {

	public static int[] rotateLeft(int[] array, int m, int n) {
		int totalLength = n + (array.length - m);
		int[] rotatedArray = new int[totalLength];

		int index = 0;
		int offset = m;
		while (offset < array.length) {
			rotatedArray[index++] = array[offset];
			offset++;
		}

		int offset = 0;
		while (offset < n) {
			rotatedArray[index++] = array[offset];
			offset++;
		}

		return rotatedArray;
	}
}
