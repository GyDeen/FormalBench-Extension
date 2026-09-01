import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinOfThree {

	public static int minOfThree(int a, int b, int c) {
		int min = a;
		min = (b < min) ? b : min;
		min = (c < min) ? c : min;
		return min;
	}
}
