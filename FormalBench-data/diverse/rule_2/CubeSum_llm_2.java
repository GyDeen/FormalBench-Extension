import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CubeSum {

	public static int cubeSum(int n) {
		int sum = 0;
		int size = 1;
		while (size <= n) {
			sum += (2 * size) * (2 * size) * (2 * size);
			size++;
		}
		return sum;
	}
}
