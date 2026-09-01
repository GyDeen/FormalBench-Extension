
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveLast {

	public static int[] moveLast(int[] move) {
		int array = move.length;
		if (array == 0) {
			return move;
		}

		int[] num = new int[array];

		for (int index = 1; index < array; index++) {
			num[index - 1] = move[index];
		}
		num[array - 1] = move[0];

		return num;
	}
}
