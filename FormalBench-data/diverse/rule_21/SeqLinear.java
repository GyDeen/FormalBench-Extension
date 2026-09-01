
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SeqLinear {

	public static String seqLinear(int[] seqNums) {
		if (1 == seqNums.length)
			return "Linear Sequence";

		int difference = seqNums[1] - seqNums[0];

		for (int i = 1; i < seqNums.length; i++) {
			if (seqNums[i] - seqNums[i - 1] != difference) {
				return "Non Linear Sequence";
			}
		}
		return "Linear Sequence";
	}
}
