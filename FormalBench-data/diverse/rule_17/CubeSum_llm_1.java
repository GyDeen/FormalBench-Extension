import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CubeSum {

	public static int cubeSum(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			int j = 2 * i;
			int temp = 2 * i;
			int tmp = 2 * i;
			sum += (j) * (temp) * (tmp);
		}
		return sum;
	}
}
