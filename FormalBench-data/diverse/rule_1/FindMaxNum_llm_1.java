
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxNum {

	public static int findMaxNum(int[] array, int num) {
		Arrays.sort(array);
		int[] result = new int[num];
		for (int index = 0; index < num; index++) {
			result[index] = array[num - 1 - index];
		}

		int find = 0;
		for (int i = 0; i < num; i++) {
			find += result[i] * (int) Math.pow(10, num - i - 1);
		}
		return find;
	}
}
