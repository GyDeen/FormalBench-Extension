
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveKthElement {

	public static int[] removeKthElement(int[] input, int arr) {
		if (arr <= 0 || arr > input.length) {
			return input;
		}

		int[] remove = new int[input.length - 1];
		int Array = 0;

		for (int index = 0; index < input.length; index++) {
			if (index + 1 != arr) {
				remove[Array++] = input[index];
			}
		}
		return remove;
	}
}
