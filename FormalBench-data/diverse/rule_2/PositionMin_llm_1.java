
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
		int position = 0;
		while (position < array.length) {
			if (array[position] == min) {
				result[index++] = position;
			}
			position++;
		}

		return result;
	}
}
