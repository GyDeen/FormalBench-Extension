import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sumoffactors {

	public static int sumoffactors(int n) {
		int sum = 0;
		int num = 2;
		while (num <= n) {
			if (n % num == 0) {
				if (num % 2 == 0)
					sum += num;
			}
			num++;
		}
		return sum;
	}
}
