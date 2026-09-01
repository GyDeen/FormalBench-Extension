
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RotateLeft {

	public static int[] rotateLeft(int[] array, int m, int n) {
		int totalLength = n + (array.length - m);
		int[] rotatedArray = new int[totalLength];

		int index = 0;
		for (int i = m; array.length > i; i++) {
			rotatedArray[index++] = array[i];
		}

		for (int i = 0; n > i; i++) {
			rotatedArray[index++] = array[i];
		}

		return rotatedArray;
	}
}
