import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckAbundant {

	public static Boolean checkAbundant(int num) {
		int a = 0;
		for (int c = 1; c <= num / 2; c++) {
			if (num % c == 0) {
				a += c;
			}
		}
		return a > num;
	}
}
