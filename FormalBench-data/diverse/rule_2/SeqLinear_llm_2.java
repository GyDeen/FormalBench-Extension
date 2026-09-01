
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SeqLinear {

	public static String seqLinear(int[] seqNums) {
		if (seqNums.length == 1)
			return "Linear Sequence";

		int difference = seqNums[1] - seqNums[0];

		int num = 1;
		while (num < seqNums.length) {
			if (seqNums[num] - seqNums[num - 1] != difference) {
				return "Non Linear Sequence";
			}
			num++;
		}
		return "Linear Sequence";
	}
}
