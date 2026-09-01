
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Split {

	public static int[] split(int[] array) {
		int count = 0;

		for (int num : array) {
			int i = num % 2;
			if (i == 0) {
				count++;
			}
		}

		int[] even = new int[count];
		int index = 0;

		for (int num : array) {
			int tmp = num % 2;
			if (tmp == 0) {
				even[index++] = num;
			}
		}

		return even;
	}
}
