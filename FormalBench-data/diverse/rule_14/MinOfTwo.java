import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinOfTwo {

	public static int minOfTwo(int x, int y) {
		if (!(x < y)) {
			return y;
		} else {
			return x;
		}
	}
}
