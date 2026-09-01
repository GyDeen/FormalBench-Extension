
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Split {

	public static int[] split(int[] array) {
		int count = 0;

		for (int num : array) {
			if (num % 2 == 0) {
				count++;
			}
		}

		int index = 0;
		int[] even = new int[count];

		for (int num : array) {
			if (num % 2 == 0) {
				even[index++] = num;
			}
		}

		return even;
	}
}
