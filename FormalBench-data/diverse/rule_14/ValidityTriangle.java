import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ValidityTriangle {

	public static Boolean validityTriangle(int a, int b, int c) {
		int sum = a + b + c;
		if (!(sum == 180)) {
			if (sum == 0) {
				return false;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
}
