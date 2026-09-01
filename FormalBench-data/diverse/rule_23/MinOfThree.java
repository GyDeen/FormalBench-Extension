import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinOfThree {

	public static int minOfThree(int a, int b, int c) {
		int min = a;
		if (min > b) {
			min = b;
		}
		if (min > c) {
			min = c;
		}
		return min;
	}
}
