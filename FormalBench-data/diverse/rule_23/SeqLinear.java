
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SeqLinear {

	public static String seqLinear(int[] seqNums) {
		if (seqNums.length == 1)
			return "Linear Sequence";

		int difference = seqNums[1] - seqNums[0];

		for (int i = 1; seqNums.length > i; i++) {
			if (difference != seqNums[i] - seqNums[i - 1]) {
				return "Non Linear Sequence";
			}
		}
		return "Linear Sequence";
	}
}
