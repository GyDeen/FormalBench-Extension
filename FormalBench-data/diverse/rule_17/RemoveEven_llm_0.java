
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveEven {

	public static int[] removeEven(int[] arr) {
		int count = 0;

		for (int num : arr) {
			int i = num % 2;
			if (i != 0) {
				count++;
			}
		}

		int[] result = new int[count];
		int index = 0;

		for (int num : arr) {
			int tmp = num % 2;
			if (tmp != 0) {
				result[index++] = num;
			}
		}

		return result;
	}
}
