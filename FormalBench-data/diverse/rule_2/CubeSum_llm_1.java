import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CubeSum {

	public static int cubeSum(int n) {
		int sum = 0;
		int result = 1;
		while (result <= n) {
			sum += (2 * result) * (2 * result) * (2 * result);
			result++;
		}
		return sum;
	}
}
