import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ApSum {

	public static int apSum(int num, int b, int c) {
		int result = 0;
		for (int count = 0; count < b; count++) {
			result += (num + count * c);
		}
		return result;
	}
}
