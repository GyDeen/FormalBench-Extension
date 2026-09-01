import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageOdd {

	public static int averageOdd(int n) {
		int sum = 0;
		int count = 0;
		int max = 1;
		while (max <= n) {
			if (max % 2 != 0) {
				sum += max;
				count++;
			}
			max++;
		}
		return sum / count;
	}
}
