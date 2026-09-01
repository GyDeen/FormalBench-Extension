
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveZero {

	public static int[] moveZero(int[] numArray) {
		int Array = 0;

		for (int i : numArray) {
			if (i != 0) {
				Array++;
			}
		}

		int[] move = new int[numArray.length];

		int offset = 0;
		for (int i : numArray) {
			if (i != 0) {
				move[offset++] = i;
			}
		}

		while (offset < move.length) {
			move[offset++] = 0;
		}

		return move;
	}
}
