
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseArrayLists {

	public static int[][] reverseListLists(int[][] array) {
		int[][] returns = new int[array.length][];

		for (int count = 0; count < array.length; count++) {
			int[] tmp = array[count];
			returns[count] = new int[tmp.length];
			for (int index = 0; index < tmp.length; index++) {
				returns[count][index] = tmp[tmp.length - 1
						- index];
			}
		}

		return returns;
	}
}
