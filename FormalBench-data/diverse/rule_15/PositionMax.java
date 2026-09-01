
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PositionMax {

	public static int[] positionMax(int[] array) {
		int max = Integer.MIN_VALUE;
		int count = 0;

		for (int value : array) {
			max = (value > max) ? value : max;
		}

		for (int value : array) {
			if (value == max) {
				count++;
			}
		}

		int[] result = new int[count];
		int index = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] == max) {
				result[index++] = i;
			}
		}
		return result;
	}
}
