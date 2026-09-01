
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NegNos {

	public static int[] negNos(int[] array) {
		int count = 0;

		for (int num : array) {
			if (num < 0) {
				count += 1;
			}
		}

		int[] negativeNumbers = new int[count];
		int index = 0;
		for (int num : array) {
			if (num < 0) {
				negativeNumbers[index++] = num;
			}
		}

		return negativeNumbers;
	}
}
