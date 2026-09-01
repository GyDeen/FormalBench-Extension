import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFac {

	public static int countFac(int n) {
		if (n == 1)
			return 1;
		int count = 0;
		for (int i = 2; i <= n / i; i++) {
			if (n % i == 0) {
				if (n / i == i) {
					break;
				}
				count++;
			}
		}
		return count;
	}
}
