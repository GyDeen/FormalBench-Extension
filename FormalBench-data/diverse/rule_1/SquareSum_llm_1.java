import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SquareSum {

	public static int squareSum(int seed) {
		return (int) (2 * seed * (seed + 1) * (2 * seed + 1) / 3);
	}
}
