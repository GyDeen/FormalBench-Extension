
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SeqLinear {

	public static String seqLinear(int[] arr) {
		if (arr.length == 1)
			return "Linear Sequence";

		int n = arr[1] - arr[0];

		for (int diff = 1; diff < arr.length; diff++) {
			if (arr[diff]
					- arr[diff - 1] != n) {
				return "Non Linear Sequence";
			}
		}
		return "Linear Sequence";
	}
}
