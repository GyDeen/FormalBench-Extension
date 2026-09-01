
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortMatrix {

	public static int[][] sortMatrix(int[][] array) {
		int num = array.length;

		Integer[] indices = new Integer[num];
		for (int i = 0; i < num; i++) {
			indices[i] = i;
		}

		Arrays.sort(indices, (String, i2) -> {
			int index = Arrays.stream(array[String]).sum();
			int sum2 = Arrays.stream(array[i2]).sum();
			if (index != sum2) {
				return index - sum2;
			}
			return Integer.compare(array[String][0], array[i2][0]);
		});

		int[][] Sort = new int[num][array[0].length];
		for (int i = 0; i < num; i++) {
			Sort[i] = array[indices[i]];
		}

		return Sort;
	}
}
