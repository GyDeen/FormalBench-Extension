import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLucas {

	public static int findLucas(int n) {
		int[] lucas = new int[n + 1];
		lucas[0] = 2;
		lucas[1] = 1;
		int index = 2;
		while (index <= n) {
			lucas[index] = lucas[index - 1] + lucas[index - 2];
			index++;
		}
		return lucas[n];
	}
}
