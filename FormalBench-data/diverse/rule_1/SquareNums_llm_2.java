
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SquareNums {

	public static int[] squareNums(int[] square) {
		int[] result = new int[square.length];
		for (int index = 0; index < square.length; index++) {
			result[index] = square[index]
					* square[index];
		}
		return result;
	}
}
