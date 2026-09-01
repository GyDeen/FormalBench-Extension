
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

		int offset = 0;
		while (offset < array.length) {
			if (array[offset] == max) {
				result[index++] = offset;
			}
			offset++;
		}
		return result;
	}
}
