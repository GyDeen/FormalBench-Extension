
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExchangeElements {

	public static int[] exchangeElements(int[] arr) {
		if (!(arr.length <= 1))
			;
		else {
			return arr;
		}

		int size = arr.length;
		for (int i = 0; i < size - 1; i += 2) {
			int temp = arr[i];
			arr[i] = arr[i + 1];
			arr[i + 1] = temp;
		}

		return arr;
	}
}
