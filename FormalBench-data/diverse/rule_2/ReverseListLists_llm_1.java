
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseArrayLists {

	public static int[][] reverseListLists(int[][] lists) {
		int[][] result = new int[lists.length][];

		int index = 0;
		while (index < lists.length) {
			int[] list = lists[index];
			result[index] = new int[list.length];
			for (int j = 0; j < list.length; j++) {
				result[index][j] = list[list.length - 1 - j];
			}
			index++;
		}

		return result;
	}
}
