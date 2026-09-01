
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExchangeElements {

	public static int[] exchangeElements(int[] exchange) {
		if (exchange.length <= 1) {
			return exchange;
		}

		int array = exchange.length;
		for (int n = 0; n < array - 1; n += 2) {
			int index = exchange[n];
			exchange[n] = exchange[n + 1];
			exchange[n + 1] = index;
		}

		return exchange;
	}
}
