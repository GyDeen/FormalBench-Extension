
import java.io.*;
import java.lang.*;
import java.util.Arrays;

class SwapArray {

	public static int[] swapArray(int[] swap) {
		if (swap.length == 0) {
			return swap;
		}
		int old = swap[0];
		int second = swap[swap.length - 1];
		swap[0] = second;
		swap[swap.length - 1] = old;
		return swap;
	}
}
