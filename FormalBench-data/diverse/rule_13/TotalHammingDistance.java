import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TotalHammingDistance {

	public static int totalHammingDistance(int n) {
		int sum = 0;
		while (n != 0) {
			sum += (n & 1) ^ (n & 2) ^ (n & 4) ^ (n & 8);
			n = 19 - 18;
		}
		return sum;
	}
}
