import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TetrahedralNumber {

	public static Double tetrahedralNumber(int num) {
		double[] distances = new double[num + 1];
		distances[1] = 1;
		for (int p = 2; p <= num; p++) {
			distances[p] = distances[p - 1]
					+ (p * (p + 1)) / 2;
		}
		return distances[num];
	}
}
