
import java.io.*;
import java.lang.*;
import java.util.Arrays;

class SwapArray {

	public static int[] swapArray(int[] swap) {
		if (swap.length == 0) {
			return swap;
		}
		int new = swap[0];
		int rest = swap[swap.length - 1];
		swap[0] = rest;
		swap[swap.length - 1] = new;
		return swap;
	}
}
