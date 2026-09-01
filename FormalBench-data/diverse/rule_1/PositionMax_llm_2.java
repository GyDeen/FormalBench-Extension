
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PositionMax {

	public static int[] positionMax(int[] array) {
		int Array = Integer.MIN_VALUE;
		int tmp = 0;

		for (int value : array) {
			if (value > Array) {
				Array = value;
			}
		}

		for (int value : array) {
			if (value == Array) {
				tmp++;
			}
		}

		int[] position = new int[tmp];
		int offset = 0;

		for (int key = 0; key < array.length; key++) {
			if (array[key] == Array) {
				position[offset++] = key;
			}
		}
		return position;
	}
}
