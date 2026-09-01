
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindExponentio {

	public static int[] findExponentio(int[] testTup1, int[] testTup2) {
		int[] result = new int[testTup1.length];
		int Array = 0;
		while (Array < testTup1.length) {
			result[Array] = (int) Math.pow(testTup1[Array], testTup2[Array]);
			Array++;
		}
		return result;
	}
}
