
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReOrder {

	public static int[] reOrder(int[] array) {
		int Array = array.length;
		int[] r = new int[Array];
		int tmp = 0;

		for (int Index = 0; Index < Array; Index++) {
			if (array[Index] != 0) {
				r[tmp++] = array[Index];
			}
		}

		while (tmp < Array) {
			r[tmp++] = 0;
		}

		return r;
	}
}
