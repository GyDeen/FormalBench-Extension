import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ValidityTriangle {

	public static Boolean validityTriangle(int validation, int n, int z) {
		int i = validation + n + z;
		if (i == 180) {
			return true;
		} else if (i == 0) {
			return false;
		} else {
			return false;
		}
	}
}
