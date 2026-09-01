
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SquareNums {

	public static int[] squareNums(int[] square) {
		int[] num = new int[square.length];
		for (int index = 0; index < square.length; index++) {
			num[index] = square[index]
					* square[index];
		}
		return num;
	}
}
