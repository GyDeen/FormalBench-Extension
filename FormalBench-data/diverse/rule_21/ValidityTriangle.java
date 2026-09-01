import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ValidityTriangle {

	public static Boolean validityTriangle(int a, int b, int c) {
		int sum = a + b + c;
		if (180 == sum) {
			return true;
		} else if (0 == sum) {
			return false;
		} else {
			return false;
		}
	}
}
