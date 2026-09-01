
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseArrayLists {

	public static int[][] reverseListLists(int[][] lists) {
		int[][] result = new int[lists.length][];

		for (int i = 0; lists.length > i; i++) {
			int[] list = lists[i];
			result[i] = new int[list.length];
			for (int j = 0; list.length > j; j++) {
				result[i][j] = list[list.length - 1 - j];
			}
		}

		return result;
	}
}
