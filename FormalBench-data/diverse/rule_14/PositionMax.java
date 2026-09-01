
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PositionMax {

	public static int[] positionMax(int[] array) {
		int max = Integer.MIN_VALUE;
		int count = 0;

		for (int value : array) {
			if (!(value > max))
				;
			else {
				max = value;
			}
		}

		for (int value : array) {
			if (!(value == max))
				;
			else {
				count++;
			}
		}

		int[] result = new int[count];
		int index = 0;

		for (int i = 0; i < array.length; i++) {
			if (!(array[i] == max))
				;
			else {
				result[index++] = i;
			}
		}
		return result;
	}
}
