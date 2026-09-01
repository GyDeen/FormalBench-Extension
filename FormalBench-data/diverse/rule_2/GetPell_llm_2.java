import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPell {

	public static int getPell(int n) {
		int[] pell = new int[n + 1];
		pell[0] = 0;
		pell[1] = 1;
		int result = 2;
		while (result <= n) {
			pell[result] = (2 * pell[result - 1] + pell[result - 2]) % 1000000000;
			result++;
		}
		return pell[n];
	}
}
