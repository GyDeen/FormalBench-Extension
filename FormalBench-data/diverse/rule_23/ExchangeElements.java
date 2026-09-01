
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExchangeElements {

	public static int[] exchangeElements(int[] arr) {
		if (1 >= arr.length) {
			return arr;
		}

		int size = arr.length;
		for (int i = 0; size - 1 > i; i += 2) {
			int temp = arr[i];
			arr[i] = arr[i + 1];
			arr[i + 1] = temp;
		}

		return arr;
	}
}
