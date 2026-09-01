
import java.io.*;
import java.lang.*;

class PositionMin {

	public static int[] positionMin(int[] array) {
		int min = array[0];
		int count = 0;

		for (int value : array) {
			if (value < min) {
				min = value;
			}
		}
		for (int value : array) {
			if (value == min) {
				count++;
			}
		}

		int index = 0;
		int[] result = new int[count];
		for (int i = 0; i < array.length; i++) {
			if (array[i] == min) {
				result[index++] = i;
			}
		}

		return result;
	}
}
