
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SeqLinear {

	public static String seqLinear(int[] arr) {
		if (arr.length == 1)
			return "Linear Sequence";

		int seq = arr[1] - arr[0];

		for (int len = 1; len < arr.length; len++) {
			if (arr[len]
					- arr[len - 1] != seq) {
				return "Non Linear Sequence";
			}
		}
		return "Linear Sequence";
	}
}
