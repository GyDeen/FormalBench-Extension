import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageEven {

	public static int averageEven(int n) {
		int sum = 0;
		int count = 0;
		int average = 1;
		while (average <= n) {
			if (average % 2 == 0) {
				sum += average;
				count++;
			}
			average++;
		}
		return sum / count;
	}
}
