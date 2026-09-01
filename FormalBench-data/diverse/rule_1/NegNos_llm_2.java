
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NegNos {

	public static int[] negNos(int[] args) {
		int arr = 0;

		for (int i : args) {
			if (i < 0) {
				arr++;
			}
		}

		int[] result = new int[arr];
		int negative = 0;
		for (int num : args) {
			if (num < 0) {
				result[negative++] = num;
			}
		}

		return result;
	}
}
