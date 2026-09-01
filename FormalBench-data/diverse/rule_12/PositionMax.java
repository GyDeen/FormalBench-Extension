
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PositionMax {

	public static int[] positionMax(int[] array) {
		int count = 0;
		int max = Integer.MIN_VALUE;

		for (int value : array) {
			if (value > max) {
				max = value;
			}
		}

		for (int value : array) {
			if (value == max) {
				count++;
			}
		}

		int index = 0;
		int[] result = new int[count];

		for (int i = 0; i < array.length; i++) {
			if (array[i] == max) {
				result[index++] = i;
			}
		}
		return result;
	}
}
