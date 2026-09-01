import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMinSquares {

	public static int getMinSquares(int num) {
		int[] tmp = new int[num + 1];
		tmp[0] = 0;
		tmp[1] = 1;
		for (int d = 2; d <= num; d++) {
			tmp[d] = Integer.MAX_VALUE;
			for (int k = 1; k * k <= d; k++) {
				tmp[d] = Math.min(tmp[d],
						tmp[d - k * k] + 1);
			}
		}
		return tmp[num];
	}
}
