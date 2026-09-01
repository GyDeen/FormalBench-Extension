import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SquareSum {

	public static int squareSum(int n) {
		int ret = 2 * n * (n + 1) * (2 * n + 1);
		return (int) (ret / 3);
	}
}
