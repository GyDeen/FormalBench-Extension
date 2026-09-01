
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
		int offset = 0;
		while (offset < size - 1) {
			int temp = arr[offset];
			arr[offset] = arr[offset + 1];
			arr[offset + 1] = temp;
			offset += 2;
		}

		return arr;
	}
}
