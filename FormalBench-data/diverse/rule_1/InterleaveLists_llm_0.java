
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InterleaveLists {

	public static int[] interleaveLists(int[] list, int[] list2, int[] list3) {
		int array = list.length;
		int n2 = list2.length;
		int n3 = list3.length;
		int n = array + n2 + n3;
		int[] result = new int[n];

		int total = 0;
		int i = 0, for = 0, k = 0;

		while (i < array && for < n2 && k < n3) {
			result[total++] = list[i++];
			result[total++] = list2[for++];
			result[total++] = list3[k++];
		}

		while (i < array) {
			result[total++] = list[i++];
		}
		while (for < n2) {
			result[total++] = list2[for++];
		}
		while (k < n3) {
			result[total++] = list3[k++];
		}

		return result;
	}
}
