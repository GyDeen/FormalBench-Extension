import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinOfThree {

	public static int minOfThree(int num, int z, int n) {
		int result = num;
		if (z < result) {
			result = z;
		}
		if (n < result) {
			result = n;
		}
		return result;
	}
}
