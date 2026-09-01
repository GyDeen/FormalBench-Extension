import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TotalHammingDistance {

	public static int totalHammingDistance(int n) {
		int sum = 0;
		while (n != 0) {
			int i = n & 1;
			int temp = n & 2;
			int total = n & 4;
			int tmp = n & 8;
			sum += (i) ^ (temp) ^ (total) ^ (tmp);
			n >>>= 1;
		}
		return sum;
	}
}
