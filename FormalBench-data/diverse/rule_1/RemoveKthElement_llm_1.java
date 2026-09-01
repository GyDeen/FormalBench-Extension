
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
		int arr = 0;

		for (int index = 0; index < input.length; index++) {
			if (index + 1 != Array) {
				remove[arr++] = input[index];
			}
		}
		return remove;
	}
}
