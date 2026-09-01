import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSquares {

	public static int countSquares(int count, int c) {
		if (c < count) {
			int r = count;
			count = c;
			c = r;
		}
		return ((count * (count + 1) * (2 * count + 1) / 6
				+ (c - count) * count * (count + 1) / 2));
	}
}
