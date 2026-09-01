import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NextPerfectSquare {

	public static int nextPerfectSquare(int num) {
		int result = 1;
		int j = 1;
		while (j <= num) {
			j = result * result;
			result++;
		}
		return j;
	}
}
