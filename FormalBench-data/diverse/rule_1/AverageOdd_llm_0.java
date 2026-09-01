import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageOdd {

	public static int averageOdd(int size) {
		int average = 0;
		int total = 0;
		for (int c = 1; c <= size; c++) {
			if (c % 2 != 0) {
				average += c;
				total++;
			}
		}
		return average / total;
	}
}
