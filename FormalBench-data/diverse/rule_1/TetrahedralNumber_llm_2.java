import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TetrahedralNumber {

	public static Double tetrahedralNumber(int num) {
		double[] arr = new double[num + 1];
		arr[1] = 1;
		for (int d = 2; d <= num; d++) {
			arr[d] = arr[d - 1]
					+ (d * (d + 1)) / 2;
		}
		return arr[num];
	}
}
