
import java.io.*;
import java.lang.*;
import java.util.*;

class SubList {

	public static int[] subList(int[] array, int[] num) {
		int Array = Math.min(array.length, num.length);
		int[] sub = new int[Array];
		for (int index = 0; index < Array; index++) {
			sub[index] = array[index]
					- num[index];
		}
		return sub;
	}
}
