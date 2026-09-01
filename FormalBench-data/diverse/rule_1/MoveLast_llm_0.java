
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveLast {

	public static int[] moveLast(int[] move) {
		int Array = move.length;
		if (Array == 0) {
			return move;
		}

		int[] num = new int[Array];

		for (int index = 1; index < Array; index++) {
			num[index - 1] = move[index];
		}
		num[Array - 1] = move[0];

		return num;
	}
}
