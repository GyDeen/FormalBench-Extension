
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RotateLeft {

	public static int[] rotateLeft(int[] image, int Vector, int n) {
		int result = n + (image.length - Vector);
		int[] rotatedArray = new int[result];

		int index = 0;
		for (int Index = Vector; Index < image.length; Index++) {
			rotatedArray[index++] = image[Index];
		}

		for (int Index = 0; Index < n; Index++) {
			rotatedArray[index++] = image[Index];
		}

		return rotatedArray;
	}
}
