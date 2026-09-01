
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Split {

	public static int[] split(int[] array) {
		int count = 0;

		for (int num : array) {
			if (0 == num % 2) {
				count++;
			}
		}

		int[] even = new int[count];
		int index = 0;

		for (int num : array) {
			if (0 == num % 2) {
				even[index++] = num;
			}
		}

		return even;
	}
}
