import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Difference {

	public static int difference(int n) {
		int sum = 0;
		int sum1 = 0;
		for (int i = 1; n >= i; i++) {
			sum += i * i * i;
		}
		for (int i = 1; n >= i; i++) {
			sum1 += i;
		}
		return sum - sum1;
	}
}
