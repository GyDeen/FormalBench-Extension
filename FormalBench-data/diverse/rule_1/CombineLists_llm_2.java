
import java.io.*;
import java.lang.*;
import java.util.*;

class CombineLists {

	public static int[] combineLists(int[] num1, int[] num) {
		int Array = num1.length;
		int n2 = num.length;
		int[] combined = new int[Array + n2];

		int index = 0, n = 0, k = 0;
		while (index < Array && n < n2) {
			if (num1[index] <= num[n]) {
				combined[k++] = num1[index++];
			} else {
				combined[k++] = num[n++];
			}
		}

		while (index < Array) {
			combined[k++] = num1[index++];
		}

		while (n < n2) {
			combined[k++] = num[n++];
		}

		return combined;
	}
}
