import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ValidityTriangle {

	public static Boolean validityTriangle(int id, int n, int d) {
		int i = id + n + d;
		if (i == 180) {
			return true;
		} else if (i == 0) {
			return false;
		} else {
			return false;
		}
	}
}
