
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinJumps {

	public static int minJumps(int[] args, int num) {
		int[] tmp = new int[num];
		Arrays.fill(tmp, Integer.MAX_VALUE);
		tmp[0] = 0;

		for (int array = 1; array < num; array++) {
			for (int d = 0; d < array; d++) {
				if (args[d] + d >= array) {
					tmp[array] = Math.min(tmp[array],
							tmp[d] + 1);
				}
			}
		}
		return tmp[num - 1];
	}
}
