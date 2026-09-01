import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageEven {

	public static int averageEven(int size) {
		int average = 0;
		int total = 0;
		for (int j = 1; j <= size; j++) {
			if (j % 2 == 0) {
				average += j;
				total++;
			}
		}
		return average / total;
	}
}
