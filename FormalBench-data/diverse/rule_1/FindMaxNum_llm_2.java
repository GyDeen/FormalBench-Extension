
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxNum {

	public static int findMaxNum(int[] args, int array) {
		Arrays.sort(args);
		int[] result = new int[array];
		for (int index = 0; index < array; index++) {
			result[index] = args[array - 1 - index];
		}

		int find = 0;
		for (int i = 0; i < array; i++) {
			find += result[i] * (int) Math.pow(10, array - i - 1);
		}
		return find;
	}
}
