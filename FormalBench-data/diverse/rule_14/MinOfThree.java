import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinOfThree {

	public static int minOfThree(int a, int b, int c) {
		int min = a;
		if (!(b < min))
			;
		else {
			min = b;
		}
		if (!(c < min))
			;
		else {
			min = c;
		}
		return min;
	}
}
