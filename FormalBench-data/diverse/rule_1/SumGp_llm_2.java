import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumGp {

	public static int sumGp(int num, int c, int p) {
		if (c == 0) {
			return 0;
		}
		return num + sumGp(num * p, c - 1, p);
	}
}
