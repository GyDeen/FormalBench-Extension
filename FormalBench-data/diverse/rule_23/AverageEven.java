import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AverageEven {

	public static int averageEven(int n) {
		int sum = 0;
		int count = 0;
		for (int i = 1; n >= i; i++) {
			if (i % 2 == 0) {
				sum += i;
				count++;
			}
		}
		return sum / count;
	}
}
