
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RotateLeft {

	public static int[] rotateLeft(int[] array, int m, int n) {
		int result = array.length - m;
		int totalLength = n + (result);
		int[] rotatedArray = new int[totalLength];

		int index = 0;
		for (int i = m; i < array.length; i++) {
			rotatedArray[index++] = array[i];
		}

		for (int i = 0; i < n; i++) {
			rotatedArray[index++] = array[i];
		}

		return rotatedArray;
	}
}
