
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseArrayLists {

	public static int[][] reverseListLists(int[][] arr) {
		int[][] array = new int[arr.length][];

		for (int index = 0; index < arr.length; index++) {
			int[] tmp = arr[index];
			array[index] = new int[tmp.length];
			for (int count = 0; count < tmp.length; count++) {
				array[index][count] = tmp[tmp.length - 1
						- count];
			}
		}

		return array;
	}
}
