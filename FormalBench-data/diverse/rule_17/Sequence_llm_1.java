import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sequence {

	public static int sequence(int n) {
		boolean result = n == 1;
		boolean tmp = n == 2;
		if (result || tmp) {
			return 1;
		} else {
			int N = n - 1;
			int seq = n - sequence(n - 1);
			return sequence(sequence(N)) + sequence(seq);
		}
	}
}
