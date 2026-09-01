import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPell {

	public static int getPell(int seed) {
		int[] arr = new int[seed + 1];
		arr[0] = 0;
		arr[1] = 1;
		for (int p = 2; p <= seed; p++) {
			arr[p] = (2 * arr[p - 1]
					+ arr[p - 2]) % 1000000000;
		}
		return arr[seed];
	}
}
