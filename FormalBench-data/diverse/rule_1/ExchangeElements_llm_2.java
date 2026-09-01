
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExchangeElements {

	public static int[] exchangeElements(int[] input) {
		if (input.length <= 1) {
			return input;
		}

		int array = input.length;
		for (int n = 0; n < array - 1; n += 2) {
			int j = input[n];
			input[n] = input[n + 1];
			input[n + 1] = j;
		}

		return input;
	}
}
