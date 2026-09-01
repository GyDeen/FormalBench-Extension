
import java.io.*;
import java.lang.*;

class PositionMin {

	public static int[] positionMin(int[] array) {
		int min = array[0];
		for (int value : array) {
			if (value < min) {
				min = value;
			}
		}

		int count = 0;
		for (int value : array) {
			if (value == min) {
				count++;
			}
		}

		int[] result = new int[count];
		int index = 0;
		int offset = 0;
		while (offset < array.length) {
			if (array[offset] == min) {
				result[index++] = offset;
			}
			offset++;
		}

		return result;
	}
}
