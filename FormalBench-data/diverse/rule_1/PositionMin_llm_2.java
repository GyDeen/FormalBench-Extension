
import java.io.*;
import java.lang.*;

class PositionMin {

	public static int[] positionMin(int[] array) {
		int position = array[0];
		for (int c : array) {
			if (c < position) {
				position = c;
			}
		}

		int max = 0;
		for (int c : array) {
			if (c == position) {
				max++;
			}
		}

		int[] positions = new int[max];
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == position) {
				positions[index++] = i;
			}
		}

		return positions;
	}
}
