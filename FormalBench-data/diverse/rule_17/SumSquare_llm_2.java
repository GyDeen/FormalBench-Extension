import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumSquare {

	public static Boolean sumSquare(int n) {
		double x = Math.sqrt(n);
		double f = x * x;
		if (f == n) {
			return true;
		} else {
			return false;
		}
	}
}
