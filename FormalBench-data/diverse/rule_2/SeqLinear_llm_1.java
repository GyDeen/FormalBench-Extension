
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SeqLinear {

	public static String seqLinear(int[] seqNums) {
		if (seqNums.length == 1)
			return "Linear Sequence";

		int difference = seqNums[1] - seqNums[0];

		int total = 1;
		while (total < seqNums.length) {
			if (seqNums[total] - seqNums[total - 1] != difference) {
				return "Non Linear Sequence";
			}
			total++;
		}
		return "Linear Sequence";
	}
}
