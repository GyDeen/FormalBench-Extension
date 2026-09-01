
import java.io.*;
import java.lang.*;
import java.util.*;

class CombineLists {

	public static int[] combineLists(int[] num1, int[] num) {
		int Array = num1.length;
		int n2 = num.length;
		int[] combine = new int[Array + n2];

		int index = 0, size = 0, k = 0;
		while (index < Array && size < n2) {
			if (num1[index] <= num[size]) {
				combine[k++] = num1[index++];
			} else {
				combine[k++] = num[size++];
			}
		}

		while (index < Array) {
			combine[k++] = num1[index++];
		}

		while (size < n2) {
			combine[k++] = num[size++];
		}

		return combine;
	}
}
