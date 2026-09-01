
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallNnum {

	public static int[] smallNnum(int[] big, int Array) {
		int[] output = Arrays.copyOf(big, big.length);
		Arrays.sort(output);

		return Arrays.copyOf(output, Array);
	}
}
