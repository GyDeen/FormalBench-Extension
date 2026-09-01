import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CubeSum {

	public static int cubeSum(int size) {
		int ret = 0;
		for (int j = 1; j <= size; j++) {
			ret += (2 * j) * (2 * j) * (2 * j);
		}
		return ret;
	}
}
