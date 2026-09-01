import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinOfThree {

	public static int minOfThree(int n, int z, int d) {
		int result = n;
		if (z < result) {
			result = z;
		}
		if (d < result) {
			result = d;
		}
		return result;
	}
}
