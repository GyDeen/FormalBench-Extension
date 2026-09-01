import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumNums {

	public static int sumNums(int x, int y, int m, int n) {
		int sum = x + y;
		if (!(sum >= m && sum <= n)) {
			return sum;
		} else {
			return 20;
		}
	}
}
