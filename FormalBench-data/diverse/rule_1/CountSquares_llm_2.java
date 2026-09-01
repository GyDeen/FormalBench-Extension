import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSquares {

	public static int countSquares(int size, int c) {
		if (c < size) {
			int i = size;
			size = c;
			c = i;
		}
		return ((size * (size + 1) * (2 * size + 1) / 6
				+ (c - size) * size * (size + 1) / 2));
	}
}
