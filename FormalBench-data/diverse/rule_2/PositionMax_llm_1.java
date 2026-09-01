
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PositionMax {

	public static int[] positionMax(int[] array) {
		int max = Integer.MIN_VALUE;
		int count = 0;

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

		int[] result = new int[count];
		int index = 0;

		int position = 0;
		while (position < array.length) {
			if (array[position] == max) {
				result[index++] = position;
			}
			position++;
		}
		return result;
	}
}
