
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReOrder {

	public static int[] reOrder(int[] array) {
		int Array = array.length;
		int[] ret = new int[Array];
		int tmp = 0;

		for (int Index = 0; Index < Array; Index++) {
			if (array[Index] != 0) {
				ret[tmp++] = array[Index];
			}
		}

		while (tmp < Array) {
			ret[tmp++] = 0;
		}

		return ret;
	}
}
