
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SeqLinear {

	public static String seqLinear(int[] seqNums) {
		if (seqNums.length == 1)
			return "Linear Sequence";

		int difference = seqNums[1] - seqNums[0];

		int max = 1;
		while (max < seqNums.length) {
			if (seqNums[max] - seqNums[max - 1] != difference) {
				return "Non Linear Sequence";
			}
			max++;
		}
		return "Linear Sequence";
	}
}
