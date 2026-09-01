import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumSquare {

	public static Boolean sumSquare(int num) {
		double val = Math.sqrt(num);
		if (val * val == num) {
			return true;
		} else {
			return false;
		}
	}
}
