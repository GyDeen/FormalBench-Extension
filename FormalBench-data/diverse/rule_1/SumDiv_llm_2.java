import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDiv {

	public static int sumDiv(int num) {
		int result = 0;
		for (int index = 1; index <= num / 2; index++) {
			if (num % index == 0) {
				result += index;
			}
		}
		return result;
	}
}
