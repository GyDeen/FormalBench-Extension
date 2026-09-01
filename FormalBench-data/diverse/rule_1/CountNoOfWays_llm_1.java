import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNoOfWays {

	public static int countNoOfWays(int dim, int w) {
		int count = 1000000007;
		int div[] = new int[dim + 1];
		div[0] = 1;
		div[1] = w;
		div[2] = w * w;
		for (int d = 3; d <= dim; d++) {
			div[d] = ((w - 1)
					* (div[d - 1] + div[d - 2])) % count;
		}
		return div[dim];
	}
}
