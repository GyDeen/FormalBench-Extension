
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveKthElement {

	public static int[] removeKthElement(int[] input, int Array) {
		if (Array <= 0 || Array > input.length) {
			return input;
		}

		int[] remove = new int[input.length - 1];
		int index = 0;

		for (int c = 0; c < input.length; c++) {
			if (c + 1 != Array) {
				remove[index++] = input[c];
			}
		}
		return remove;
	}
}
