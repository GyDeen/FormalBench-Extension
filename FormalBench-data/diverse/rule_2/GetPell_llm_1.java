import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPell {

	public static int getPell(int n) {
		int[] pell = new int[n + 1];
		pell[0] = 0;
		pell[1] = 1;
		int count = 2;
		while (count <= n) {
			pell[count] = (2 * pell[count - 1] + pell[count - 2]) % 1000000000;
			count++;
		}
		return pell[n];
	}
}
