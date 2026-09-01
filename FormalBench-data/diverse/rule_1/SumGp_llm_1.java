import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumGp {

	public static int sumGp(int size, int b, int p) {
		if (b == 0) {
			return 0;
		}
		return size + sumGp(size * p, b - 1, p);
	}
}
