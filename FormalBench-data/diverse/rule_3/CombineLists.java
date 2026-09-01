
import java.io.*;
import java.lang.*;
import java.util.*;

class CombineLists {

	public static int[] combineLists(int[] num1, int[] num2) {
		int n1 = num1.length;
		int n2 = num2.length;
		int[] result = new int[n1 + n2];

		int i = 0, j = 0, k = 0;
		for (; i < n1 && j < n2;) {
			if (num1[i] <= num2[j]) {
				result[k++] = num1[i++];
			} else {
				result[k++] = num2[j++];
			}
		}

		for (; i < n1;) {
			result[k++] = num1[i++];
		}

		for (; j < n2;) {
			result[k++] = num2[j++];
		}

		return result;
	}
}
