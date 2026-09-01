import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFac {

	public static int countFac(int n) {
		int count = 0;
		if (1 == n)
			return 1;
		for (int i = 2; i <= n / i; i++) {
			if (0 == n % i) {
				count++;
				if (i == n / i) {
					break;
				}
			}
		}
		return count;
	}
}
