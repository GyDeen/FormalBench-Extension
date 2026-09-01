
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SeqLinear {

	public static String seqLinear(int[] seqNums) {
		if (seqNums.length == 1)
			return "Linear Sequence";

		int difference = seqNums[1] - seqNums[0];

		for (int i = 1; i < seqNums.length; i++) {
			int total = seqNums[i] - seqNums[i - 1];
			if (total != difference) {
				return "Non Linear Sequence";
			}
		}
		return "Linear Sequence";
	}
}
