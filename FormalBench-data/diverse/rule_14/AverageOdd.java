import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageOdd {

	public static int averageOdd(int n) {
		int sum = 0;
		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (!(i % 2 != 0))
				;
			else {
				sum += i;
				count++;
			}
		}
		return sum / count;
	}
}
