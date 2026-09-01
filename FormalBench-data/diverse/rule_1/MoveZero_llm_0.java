
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

		int total = 0;
		for (int i : numArray) {
			if (i != 0) {
				move[total++] = i;
			}
		}

		while (total < move.length) {
			move[total++] = 0;
		}

		return move;
	}
}
