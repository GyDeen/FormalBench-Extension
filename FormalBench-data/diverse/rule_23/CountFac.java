import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFac {

	public static int countFac(int n) {
		int count = 0;
		if (n == 1)
			return 1;
		for (int i = 2; n / i >= i; i++) {
			if (n % i == 0) {
				count++;
				if (n / i == i) {
					break;
				}
			}
		}
		return count;
	}
}
