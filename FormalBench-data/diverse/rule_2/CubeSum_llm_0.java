import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CubeSum {

	public static int cubeSum(int n) {
		int sum = 0;
		int total = 1;
		while (total <= n) {
			sum += (2 * total) * (2 * total) * (2 * total);
			total++;
		}
		return sum;
	}
}
