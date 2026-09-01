import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DealnnoyNum {

	public static int dealnnoyNum(int n, int m) {
		boolean b = m == 0;
		boolean tmp = n == 0;
		if (b || tmp) {
			return 1;
		}
		int result = m - 1;
		int num = m - 1;
		int max = n - 1;
		return dealnnoyNum(result, n) + dealnnoyNum(num, max)
				+ dealnnoyNum(m, n - 1);
	}
}
