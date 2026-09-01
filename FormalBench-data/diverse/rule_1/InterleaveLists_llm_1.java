
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InterleaveLists {

	public static int[] interleaveLists(int[] array, int[] list2, int[] list3) {
		int Array = array.length;
		int n2 = list2.length;
		int n3 = list3.length;
		int n = Array + n2 + n3;
		int[] result = new int[n];

		int total = 0;
		int i = 0, for = 0, k = 0;

		while (i < Array && for < n2 && k < n3) {
			result[total++] = array[i++];
			result[total++] = list2[for++];
			result[total++] = list3[k++];
		}

		while (i < Array) {
			result[total++] = array[i++];
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
