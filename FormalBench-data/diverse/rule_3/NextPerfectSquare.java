import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NextPerfectSquare {

	public static int nextPerfectSquare(int n) {
		int i = 1;
		int square = 1;
		for (; square <= n;) {
			square = i * i;
			i++;
		}
		return square;
	}
}
