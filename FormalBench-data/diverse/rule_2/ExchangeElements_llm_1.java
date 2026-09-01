
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
		int index = 0;
		while (index < size - 1) {
			int temp = arr[index];
			arr[index] = arr[index + 1];
			arr[index + 1] = temp;
			index += 2;
		}

		return arr;
	}
}
