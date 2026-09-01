import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NextPerfectSquare {

	public static int nextPerfectSquare(int n) {
		int square = 1;
		int i = 1;
		while (square <= n) {
			square = i * i;
			i++;
		}
		return square;
	}
}
