import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumSquare {

	public static Boolean sumSquare(int number) {
		double sum = Math.sqrt(number);
		if (sum * sum == number) {
			return true;
		} else {
			return false;
		}
	}
}
