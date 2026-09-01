import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumSquare {

	public static Boolean sumSquare(int num) {
		double sum = Math.sqrt(num);
		if (sum * sum == num) {
			return true;
		} else {
			return false;
		}
	}
}
