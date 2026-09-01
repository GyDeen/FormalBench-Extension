import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNoOfWays {

	public static int countNoOfWays(int count, int w) {
		int result = 1000000007;
		int div[] = new int[count + 1];
		div[0] = 1;
		div[1] = w;
		div[2] = w * w;
		for (int d = 3; d <= count; d++) {
			div[d] = ((w - 1)
					* (div[d - 1] + div[d - 2])) % result;
		}
		return div[count];
	}
}
