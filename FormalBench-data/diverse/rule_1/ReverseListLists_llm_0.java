
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseArrayLists {

	public static int[][] reverseListLists(int[][] array) {
		int[][] returns = new int[array.length][];

		for (int index = 0; index < array.length; index++) {
			int[] tmp = array[index];
			returns[index] = new int[tmp.length];
			for (int count = 0; count < tmp.length; count++) {
				returns[index][count] = tmp[tmp.length - 1
						- count];
			}
		}

		return returns;
	}
}
