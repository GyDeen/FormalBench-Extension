import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPell {

	public static int getPell(int seed) {
		int[] p = new int[seed + 1];
		p[0] = 0;
		p[1] = 1;
		for (int count = 2; count <= seed; count++) {
			p[count] = (2 * p[count - 1]
					+ p[count - 2]) % 1000000000;
		}
		return p[seed];
	}
}
