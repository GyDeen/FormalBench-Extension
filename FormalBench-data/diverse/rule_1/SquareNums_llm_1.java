
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SquareNums {

	public static int[] squareNums(int[] array) {
		int[] square = new int[array.length];
		for (int index = 0; index < array.length; index++) {
			square[index] = array[index]
					* array[index];
		}
		return square;
	}
}
