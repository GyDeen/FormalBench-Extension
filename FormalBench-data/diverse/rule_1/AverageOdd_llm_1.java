import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageOdd {

	public static int averageOdd(int size) {
		int ret = 0;
		int total = 0;
		for (int c = 1; c <= size; c++) {
			if (c % 2 != 0) {
				ret += c;
				total++;
			}
		}
		return ret / total;
	}
}
