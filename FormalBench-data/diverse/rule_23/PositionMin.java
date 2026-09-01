
import java.io.*;
import java.lang.*;

class PositionMin {

	public static int[] positionMin(int[] array) {
		int min = array[0];
		for (int value : array) {
			if (min > value) {
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
		for (int i = 0; array.length > i; i++) {
			if (array[i] == min) {
				result[index++] = i;
			}
		}

		return result;
	}
}
