import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sumoffactors {

	public static int sumoffactors(int n) {
		int sum = 0;
		int max = 2;
		while (max <= n) {
			if (n % max == 0) {
				if (max % 2 == 0)
					sum += max;
			}
			max++;
		}
		return sum;
	}
}
