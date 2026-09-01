
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseArrayLists {

	public static int[][] reverseListLists(int[][] lists) {
		int[][] result = new int[lists.length][];

		int Array = 0;
		while (Array < lists.length) {
			int[] list = lists[Array];
			result[Array] = new int[list.length];
			for (int j = 0; j < list.length; j++) {
				result[Array][j] = list[list.length - 1 - j];
			}
			Array++;
		}

		return result;
	}
}
