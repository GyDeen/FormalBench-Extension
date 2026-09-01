
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallNnum {

	public static int[] smallNnum(int[] big, int Array) {
		int[] input = Arrays.copyOf(big, big.length);
		Arrays.sort(input);

		return Arrays.copyOf(input, Array);
	}
}
