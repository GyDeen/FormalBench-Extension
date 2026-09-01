
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReOrder {

	public static int[] reOrder(int[] array) {
		int arr = array.length;
		int[] r = new int[arr];
		int tmp = 0;

		for (int Index = 0; Index < arr; Index++) {
			if (array[Index] != 0) {
				r[tmp++] = array[Index];
			}
		}

		while (tmp < arr) {
			r[tmp++] = 0;
		}

		return r;
	}
}
