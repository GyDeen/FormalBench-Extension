import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPell {

	public static int getPell(int n) {
		int[] pell = new int[n + 1];
		pell[0] = 0;
		pell[1] = 1;
		int p = 2;
		while (p <= n) {
			pell[p] = (2 * pell[p - 1] + pell[p - 2]) % 1000000000;
			p++;
		}
		return pell[n];
	}
}
