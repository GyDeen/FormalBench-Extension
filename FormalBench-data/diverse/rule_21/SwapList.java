
import java.io.*;
import java.lang.*;
import java.util.Arrays;

class SwapArray {

	public static int[] swapArray(int[] newArray) {
		if (0 == newArray.length) {
			return newArray;
		}
		int first = newArray[0];
		int last = newArray[newArray.length - 1];
		newArray[0] = last;
		newArray[newArray.length - 1] = first;
		return newArray;
	}
}
