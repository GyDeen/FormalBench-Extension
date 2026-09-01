
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RotateLeft {

	public static int[] rotateLeft(int[] image, int Array, int n) {
		int ret = n + (image.length - Array);
		int[] rotatedArray = new int[ret];

		int index = 0;
		for (int Index = Array; Index < image.length; Index++) {
			rotatedArray[index++] = image[Index];
		}

		for (int Index = 0; Index < n; Index++) {
			rotatedArray[index++] = image[Index];
		}

		return rotatedArray;
	}
}
