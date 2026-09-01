
import java.io.*;
import java.lang.*;

class PositionMin {

	public static int[] positionMin(int[] array) {
		int positions = array[0];
		for (int pos : array) {
			if (pos < positions) {
				positions = pos;
			}
		}

		int max = 0;
		for (int pos : array) {
			if (pos == positions) {
				max++;
			}
		}

		int[] position = new int[max];
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == positions) {
				position[index++] = i;
			}
		}

		return position;
	}
}
