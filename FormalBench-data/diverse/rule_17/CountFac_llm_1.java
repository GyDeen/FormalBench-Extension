import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFac {

	public static int countFac(int n) {
		int count = 0;
		if (n == 1)
			return 1;
		int end = n / i;
		for (int i = 2; i <= end; i++) {
			int j = n % i;
			if (j == 0) {
				count++;
				int tmp = n / i;
				if (tmp == i) {
					break;
				}
			}
		}
		return count;
	}
}
