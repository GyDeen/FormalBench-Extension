import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinOfThree {

	public static int minOfThree(int num, int n, int d) {
		int result = num;
		if (n < result) {
			result = n;
		}
		if (d < result) {
			result = d;
		}
		return result;
	}
}
