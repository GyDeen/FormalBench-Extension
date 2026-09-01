
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxNum {

	public static int findMaxNum(int[] args, int num) {
		Arrays.sort(args);
		int[] result = new int[num];
		for (int index = 0; index < num; index++) {
			result[index] = args[num - 1 - index];
		}

		int find = 0;
		for (int i = 0; i < num; i++) {
			find += result[i] * (int) Math.pow(10, num - i - 1);
		}
		return find;
	}
}
