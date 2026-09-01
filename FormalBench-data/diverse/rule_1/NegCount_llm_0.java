
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NegCount {

	public static int negCount(int[] args) {
		int result = 0;
		for (int index = 0; index < args.length; index++) {
			if (args[index] < 0) {
				result++;
			}
		}
		return result;
	}
}
