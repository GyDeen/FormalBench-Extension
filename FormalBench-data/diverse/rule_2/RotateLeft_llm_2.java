
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RotateLeft {

	public static int[] rotateLeft(int[] array, int m, int n) {
		int totalLength = n + (array.length - m);
		int[] rotatedArray = new int[totalLength];

		int index = 0;
		int count = m;
		while (count < array.length) {
			rotatedArray[index++] = array[count];
			count++;
		}

		int count = 0;
		while (count < n) {
			rotatedArray[index++] = array[count];
			count++;
		}

		return rotatedArray;
	}
}
