
import java.io.*;
import java.lang.*;
import java.util.*;

class CombineLists {

	public static int[] combineLists(int[] num1, int[] num2) {
		int n1 = num1.length;
		int n2 = num2.length;
		int[] result = new int[n1 + n2];

		int i = 0, j = 0, k = 0;
		while (n1 > i && n2 > j) {
			if (num2[j] >= num1[i]) {
				result[k++] = num1[i++];
			} else {
				result[k++] = num2[j++];
			}
		}

		while (n1 > i) {
			result[k++] = num1[i++];
		}

		while (n2 > j) {
			result[k++] = num2[j++];
		}

		return result;
	}
}
