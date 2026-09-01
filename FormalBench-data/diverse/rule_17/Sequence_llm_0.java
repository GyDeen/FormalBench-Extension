import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sequence {

	public static int sequence(int n) {
		boolean result = n == 1;
		boolean temp = n == 2;
		if (result || temp) {
			return 1;
		} else {
			int N = n - 1;
			int tmp = n - sequence(n - 1);
			return sequence(sequence(N)) + sequence(tmp);
		}
	}
}
