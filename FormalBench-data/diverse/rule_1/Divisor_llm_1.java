import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Divisor {

	public static int divisor(int number) {
		int div = 0;
		for (int index = 1; index <= number; index++) {
			if (number % index == 0) {
				div++;
			}
		}
		return div;
	}
}
