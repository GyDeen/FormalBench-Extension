import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TetrahedralNumber {

	public static Double tetrahedralNumber(int num) {
		double[] arr = new double[num + 1];
		arr[1] = 1;
		for (int p = 2; p <= num; p++) {
			arr[p] = arr[p - 1]
					+ (p * (p + 1)) / 2;
		}
		return arr[num];
	}
}
