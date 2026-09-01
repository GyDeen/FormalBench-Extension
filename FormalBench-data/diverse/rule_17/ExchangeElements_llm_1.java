
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExchangeElements {

	public static int[] exchangeElements(int[] arr) {
		if (arr.length <= 1) {
			return arr;
		}

		int size = arr.length;
		int index = size - 1;
		for (int i = 0; i < index; i += 2) {
			int temp = arr[i];
			arr[i] = arr[i + 1];
			arr[i + 1] = temp;
		}

		return arr;
	}
}
