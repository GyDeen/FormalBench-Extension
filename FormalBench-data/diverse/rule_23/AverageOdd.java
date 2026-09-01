import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageOdd {

	public static int averageOdd(int n) {
		int sum = 0;
		int count = 0;
		for (int i = 1; n >= i; i++) {
			if (0 != i % 2) {
				sum += i;
				count++;
			}
		}
		return sum / count;
	}
}
