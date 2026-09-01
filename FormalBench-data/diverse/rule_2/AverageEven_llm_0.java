import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageEven {

	public static int averageEven(int n) {
		int sum = 0;
		int count = 0;
		int total = 1;
		while (total <= n) {
			if (total % 2 == 0) {
				sum += total;
				count++;
			}
			total++;
		}
		return sum / count;
	}
}
