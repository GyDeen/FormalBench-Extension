import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DealnnoyNum {

	public static int dealnnoyNum(int n, int m) {
		boolean b = m == 0;
		boolean temp = n == 0;
		if (b || temp) {
			return 1;
		}
		int result = m - 1;
		int num = m - 1;
		int tmp = n - 1;
		return dealnnoyNum(result, n) + dealnnoyNum(num, tmp)
				+ dealnnoyNum(m, n - 1);
	}
}
