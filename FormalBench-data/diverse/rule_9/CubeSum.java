import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CubeSum {

	public static int cubeSum(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum = sum + ((2 * i) * (2 * i) * (2 * i));
		}
		return sum;
	}
}
