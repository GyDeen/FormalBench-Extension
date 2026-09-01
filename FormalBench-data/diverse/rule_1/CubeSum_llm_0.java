import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CubeSum {

	public static int cubeSum(int size) {
		int result = 0;
		for (int j = 1; j <= size; j++) {
			result += (2 * j) * (2 * j) * (2 * j);
		}
		return result;
	}
}
