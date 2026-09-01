
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NegCount {

	public static int negCount(int[] args) {
		int result = 0;
		for (int c = 0; c < args.length; c++) {
			if (args[c] < 0) {
				result++;
			}
		}
		return result;
	}
}
