import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageEven {

	public static int averageEven(int n) {
		int count = 0;
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			if (i % 2 == 0) {
				count++;
				sum += i;
			}
		}
		return sum / count;
	}
}
