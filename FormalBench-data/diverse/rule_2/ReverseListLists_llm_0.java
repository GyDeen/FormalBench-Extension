
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseArrayLists {

	public static int[][] reverseListLists(int[][] lists) {
		int[][] result = new int[lists.length][];

		int array = 0;
		while (array < lists.length) {
			int[] list = lists[array];
			result[array] = new int[list.length];
			for (int j = 0; j < list.length; j++) {
				result[array][j] = list[list.length - 1 - j];
			}
			array++;
		}

		return result;
	}
}
