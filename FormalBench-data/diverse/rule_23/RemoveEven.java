
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveEven {

	public static int[] removeEven(int[] arr) {
		int count = 0;

		for (int num : arr) {
			if (0 != num % 2) {
				count++;
			}
		}

		int[] result = new int[count];
		int index = 0;

		for (int num : arr) {
			if (0 != num % 2) {
				result[index++] = num;
			}
		}

		return result;
	}
}
