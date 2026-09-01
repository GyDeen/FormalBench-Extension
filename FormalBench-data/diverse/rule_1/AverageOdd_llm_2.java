import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageOdd {

	public static int averageOdd(int size) {
		int average = 0;
		int total = 0;
		for (int index = 1; index <= size; index++) {
			if (index % 2 != 0) {
				average += index;
				total++;
			}
		}
		return average / total;
	}
}
