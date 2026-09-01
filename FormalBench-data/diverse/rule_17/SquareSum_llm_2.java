import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SquareSum {

	public static int squareSum(int n) {
		int r = 2 * n * (n + 1) * (2 * n + 1);
		return (int) (r / 3);
	}
}
