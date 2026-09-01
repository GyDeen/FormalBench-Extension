
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveEven {

	public static int[] removeEven(int[] arr) {
		int count = 0;

		for (int num : arr) {
			if (num % 2 != 0) {
				count++;
			}
		}

		int index = 0;
		int[] result = new int[count];

		for (int num : arr) {
			if (num % 2 != 0) {
				result[index++] = num;
			}
		}

		return result;
	}
}
