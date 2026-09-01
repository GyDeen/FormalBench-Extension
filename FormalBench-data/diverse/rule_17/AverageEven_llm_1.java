import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageEven {

	public static int averageEven(int n) {
		int sum = 0;
		int count = 0;
		for (int i = 1; i <= n; i++) {
			int j = i % 2;
			if (j == 0) {
				sum += i;
				count++;
			}
		}
		return sum / count;
	}
}
