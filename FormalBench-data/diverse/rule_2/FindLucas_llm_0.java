import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLucas {

	public static int findLucas(int n) {
		int[] lucas = new int[n + 1];
		lucas[0] = 2;
		lucas[1] = 1;
		int num = 2;
		while (num <= n) {
			lucas[num] = lucas[num - 1] + lucas[num - 2];
			num++;
		}
		return lucas[n];
	}
}
