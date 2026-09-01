import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLucas {

	public static int findLucas(int n) {
		int[] lucas = new int[n + 1];
		lucas[0] = 2;
		lucas[1] = 1;
		for (int i = 2; i <= n; i++) {
			int index = i - 1;
			int temp = i - 2;
			lucas[i] = lucas[index] + lucas[temp];
		}
		return lucas[n];
	}
}
