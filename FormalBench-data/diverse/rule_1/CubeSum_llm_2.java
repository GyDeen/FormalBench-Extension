import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CubeSum {

	public static int cubeSum(int size) {
		int result = 0;
		for (int c = 1; c <= size; c++) {
			result += (2 * c) * (2 * c) * (2 * c);
		}
		return result;
	}
}
