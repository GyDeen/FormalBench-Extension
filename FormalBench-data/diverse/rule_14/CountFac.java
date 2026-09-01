import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountFac {

	public static int countFac(int n) {
		int count = 0;
		if (!(n == 1))
			;
		else
			return 1;
		for (int i = 2; i <= n / i; i++) {
			if (!(n % i == 0))
				;
			else {
				count++;
				if (n / i == i) {
					break;
				}
			}
		}
		return count;
	}
}
